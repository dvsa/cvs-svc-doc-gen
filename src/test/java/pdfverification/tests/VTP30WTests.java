package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.VTP30W;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static htmlverification.framework.component.DefectSummaryComponent.*;
import static org.junit.Assert.assertTrue;

public class VTP30WTests {
    private static final int FIRST_WELSH_PAGE_NUMBER = 1;
    private PDFGenerationService pdfGenerationService;
    private HtmlGenerator htmlGenerator;
    private PDFParser pdfParser;
    private VTP30W testCertificate;
    private PdfReader pdfReader;
    private byte[] pdfData;

    public VTP30WTests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVtp30w();

        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws IOException {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

   @Test
    public void verifyWelshTitleIsOnFirstPage() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Gwrthod tystysgrif prawf MOT"));
    }

    @Test
    public void verifyWelshRfrs() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(ADVISORIES_HEADER_TEXT_WELSH_CVS));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(MAJOR_DEFECTS_HEADER_TEXT_WELSH_CVS));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(DANGEROUS_DEFECTS_HEADER_TEXT_WELSH_CVS));
    }

    @Test
    public void verifyWelshVehicleId() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Rhif adnabod cerbyd"));
    }

    @Test
    public void verifyWelshId() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Rhif adnabod"));
    }

    @Test
    public void verifyWelshCountryOfRegistration() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Gwlad cofrestru"));
    }

    @Test
    public void verifyWelshMakeAndModel() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Math a model"));
    }

    @Test
    public void verifyWelshVehicleCategory() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Categori cerbyd"));
    }

    @Test
    public void verifyWelshOdometer() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Odomedr"));
    }

    @Test
    public void verifyWelshDateOfTest() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Dyddiad y prawf"));
    }

    @Test
    public void verifyWelshLocationOfTheTest() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Lleoliad y prawf"));
    }

    @Test
    public void verifyWelshTestingOrganisationAndInspection() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Sefydliad ac arolygydd y prawf"));
    }

    @Test
    public void verifyWelshTestNumber() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Rhif prawf"));
    }

    @Test
    public void verifyRecallTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("Mae gan y cerbyd hwn wedi cael ei alw'n ôl"));
    }
}
