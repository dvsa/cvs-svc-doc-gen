package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.mot.MotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VT20Tests {

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private MotCertificate testCertificate;
    private PDFParser pdfParser;
    private byte[] pdfData;

    private static final String ISSUER_SIGNATURE = "Issuer signature";
    private static final String ISSUED_BY_DVSA = "Issued by DVSA";

    public VT20Tests() {
        this.testCertificate = CertificateTestDataProvider.getVt20();

        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
    }

    @Test
    public void verifySinglePage() throws Exception {
        PdfReader reader = pdfParser.readPdf(pdfData);

        pdfParser.getRawText(reader, 2);
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {

        this.testCertificate = CertificateTestDataProvider.getVt20HavingInvalidXMLCharacter();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        pdfParser.getRawText(reader, 1);
    }

    @Test
    public void verifyIssuerSignatureIsReplacedByDvsaWhenRequested() throws IOException {
        this.testCertificate = CertificateTestDataProvider.getMultiPageVt20WithHiddenIssuerInfo();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        // 1st page is handled differently than the subsequent pages,
        // the signature has to be verified on pages 1 & 2 to cover different cases
        assertFalse(pdfParser.getRawText(reader, 1).contains(ISSUER_SIGNATURE));
        assertFalse(pdfParser.getRawText(reader, 2).contains(ISSUER_SIGNATURE));

        assertTrue(pdfParser.getRawText(reader, 1).contains(ISSUED_BY_DVSA));
        assertTrue(pdfParser.getRawText(reader, 2).contains(ISSUED_BY_DVSA));
    }

    @Test
    public void verifyIssuerSignatureIsVisibleWhenRequested() throws IOException {
        this.testCertificate = CertificateTestDataProvider.getMultiPageVt20WithVisibleIssuerInfo();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        // 1st page is handled differently than the subsequent pages,
        // the signature has to be verified on pages 1 & 2 to cover different cases
        assertTrue(pdfParser.getRawText(reader, 1).contains(ISSUER_SIGNATURE));
        assertTrue(pdfParser.getRawText(reader, 2).contains(ISSUER_SIGNATURE));

        assertFalse(pdfParser.getRawText(reader, 1).contains(ISSUED_BY_DVSA));
        assertFalse(pdfParser.getRawText(reader, 2).contains(ISSUED_BY_DVSA));
    }

    @Test
    public void verifyIssuerSignatureShowsUpByDefaultWhenItIsNotSpecified() throws IOException {
        this.testCertificate = CertificateTestDataProvider.getMultiPageVt20WithUnspecifiedIssuerVisibilitySetting();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        // 1st page is handled differently than the subsequent pages,
        // the signature has to be verified on pages 1 & 2 to cover different cases
        assertTrue(pdfParser.getRawText(reader, 1).contains(ISSUER_SIGNATURE));
        assertTrue(pdfParser.getRawText(reader, 2).contains(ISSUER_SIGNATURE));

        assertFalse(pdfParser.getRawText(reader, 1).contains(ISSUED_BY_DVSA));
        assertFalse(pdfParser.getRawText(reader, 2).contains(ISSUED_BY_DVSA));
    }
}
