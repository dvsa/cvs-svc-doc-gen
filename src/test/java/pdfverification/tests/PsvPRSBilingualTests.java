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

public class PsvPRSBilingualTests {
    private final HtmlGenerator htmlGenerator;
    private final PDFGenerationService pdfGenerationService;
    private CvsMotCertificate testCertificate;
    private final PDFParser pdfParser;
    private PdfReader pdfReader;

    public PsvPRSBilingualTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsPsvPRSBilingual();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("MOT test certificate (PSV)"));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains("Refusal of MOT test certificate"));
        assertTrue(pdfParser.getRawText(pdfReader, 5).contains("Tystysgrif prawf MOT (PSV)"));
        assertTrue(pdfParser.getRawText(pdfReader, 7).contains("Gwrthod tystysgrif prawf MOT"));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("This vehicle has an outstanding recall"));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains("This vehicle has an outstanding recall"));
        assertTrue(pdfParser.getRawText(pdfReader, 5).contains("Mae gan y cerbyd hwn wedi cael ei alw'n ôl"));
        assertTrue(pdfParser.getRawText(pdfReader, 7).contains("Mae gan y cerbyd hwn wedi cael ei alw'n ôl"));
    }

    @Test
    public void verifySinglePageWithInvalidXMLCharacter() throws Exception {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsPsvPRSBilingualHavingInvalidXMLCharacter();
        pdfParser.getRawText(pdfReader, 1);
    }
}

