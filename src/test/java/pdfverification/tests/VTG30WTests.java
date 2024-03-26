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

public class VTG30WTests {
    private static final String CERT_NAME = "Gwrthod tystysgrif prawf MOT";

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private PDFParser pdfParser;

    private PdfReader pdfReader;
    private byte[] pdfData;

    public VTG30WTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVTG30W();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(CERT_NAME));
    }

    @Test
    public void verifySinglePageWithInvaidXMLCharacter() throws Exception {
        this.testCertificate = CvsCertificateTestDataProvider.getVTG30WHavingInvalidXMLCharacter();
        pdfParser.getRawText(pdfReader, 1);
    }
}
