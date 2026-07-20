package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.AdrIIRefusalCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AdrIITests {

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private AdrIIRefusalCertificate testCertificate;
    private PDFParser pdfParser;
    private byte[] pdfData;

    public AdrIITests() {
        this.testCertificate = CvsCertificateTestDataProvider.getAdrRefusal();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
    }

    @Test
    public void verifyDocumentHasTwoPages() throws Exception {
        PdfReader reader = pdfParser.readPdf(pdfData);
        assertEquals(2, reader.getNumberOfPages());
    }

    @Test
    public void dumpPdfForVisualInspection() throws Exception {
        String out = System.getenv("ADR_II_PDF_OUT");
        if (out != null) {
            Files.write(Paths.get(out), pdfData);
        }
    }
}
