package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public abstract class AbandonedTests {
    protected HtmlGenerator htmlGenerator;
    protected PDFGenerationService pdfGenerationService;
    protected AbandonedCertificate testCertificate;
    protected PDFParser pdfParser;
    protected PdfReader pdfReader;
    protected byte[] pdfData;

    protected static String DYNAMIC_TITLE_SECTION;
    protected static String REGULATION_LINE1;
    protected static String REGULATION_LINE2;
    protected static String VEHICLE_TYPE_TEXT_LINE1;
    protected static String VEHICLE_TYPE_TEXT_LINE2;
    protected static String VIN;
    protected static String REASONS_FOR_REFUSAL_LINE1;
    protected static String REASONS_FOR_REFUSAL_LINE2;
    protected static String ROLLING_FOOTER_LEFT;
    protected static String ADDITIONAL_COMMENTS;
    protected static String ROLLING_HEADER_LEFT;
    protected static String ROLLING_HEADER_RIGHT;
    protected static String PRINT_NAME;
    protected static String LOCATION;
    protected static String LOCATION_NUMBER;
    protected static String DATE_OF_THE_TEST;
    protected static String SECTION_TEXT;

    public AbandonedTests() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitle() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DYNAMIC_TITLE_SECTION));
    }

    @Test
    public void verifyRegulationText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REGULATION_LINE2));
    }

    @Test
    public void verifyVehicleTypeText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VEHICLE_TYPE_TEXT_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VEHICLE_TYPE_TEXT_LINE2));
    }

    @Test
    public void verifyVINText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(VIN));
    }

    @Test
    public void verifySectionText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(SECTION_TEXT));
    }

    @Test
    public void verifyReasonsForRefusal() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REASONS_FOR_REFUSAL_LINE2));
    }

    @Test
    public void verifyRollingFooterLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ROLLING_FOOTER_LEFT));
    }

    @Test
    public void verifyAdditionalComments() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ADDITIONAL_COMMENTS));
    }

    @Test
    public void verifyRollingHeaderLeftText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_LEFT));
    }

    @Test
    public void verifyRollingHeaderRightText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(ROLLING_HEADER_RIGHT));
    }

    @Test
    public void verifyPrintName() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(PRINT_NAME));
    }

    @Test
    public void verifyLocation() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION));
    }

    @Test
    public void verifyLocationNumber() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(LOCATION_NUMBER));
    }

    @Test
    public void verifyDate() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(DATE_OF_THE_TEST));
    }
}
