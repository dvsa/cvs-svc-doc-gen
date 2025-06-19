package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.CvsMotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class VTP20Tests {

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private PDFParser pdfParser;
    public PdfReader pdfReader;
    private byte[] pdfData;

    public VTP20Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVtp20();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {

        this.testCertificate = CvsCertificateTestDataProvider.getVtp20HavingInvalidXMLCharacter();
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));

        PdfReader reader = pdfParser.readPdf(pdfData);

        pdfParser.getRawText(reader, 1);
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("MOT test certificate (PSV)"));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("This vehicle has an outstanding recall"));
    }
}
