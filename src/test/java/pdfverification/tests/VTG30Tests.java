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

public class VTG30Tests {

    public HtmlGenerator htmlGenerator;
    public PDFGenerationService pdfGenerationService;
    public CvsMotCertificate testCertificate;
    public PDFParser pdfParser;
    public PdfReader pdfReader;

    public VTG30Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVtg30();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {

        this.testCertificate = CvsCertificateTestDataProvider.getVtg30HavingInvalidXMLCharacter();
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        PdfReader reader = pdfParser.readPdf(pdfData);

        pdfParser.getRawText(reader, 1);
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("Refusal of MOT test certificate"));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("This vehicle has an outstanding recall"));
    }
}
