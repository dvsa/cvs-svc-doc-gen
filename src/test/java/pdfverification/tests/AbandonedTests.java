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

import java.io.FileOutputStream;
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
    protected static String DEFECT_ADDED_DURING_TEST_TEXT;
    protected static String DO_NOT_DRIVE_UNTIL_REPAIRED_TEXT;
    protected static String DANGEROUS_DEFECTS_TEXT_LINE1;
    protected static String DANGEROUS_DEFECTS_TEXT_LINE2;
    protected static String REPAIR_IMMEDIATELY_TEXT;
    protected static String MAJOR_DEFECTS_TEXT_LINE1_1ST;
    protected static String MAJOR_DEFECTS_TEXT_LINE1_2ND;
    protected static String MAJOR_DEFECTS_TEXT_LINE2_2ND;
    protected static String REPAIR_AS_SOON_AS_POSSIBLE_TEXT;
    protected static String MINOR_DEFECTS_TEXT_LINE1;
    protected static String MONITOR_AND_REPAIR_IF_NECESSARY_TEXT;
    protected static String ADVISORY_DEFECTS_TEXT_LINE1;
    protected static String DEFECTS_RECTIFIED_AT_TIME_OF_TEST_TEXT;
    protected static String PRS_DEFECTS__TEXT_LINE1;
    protected static String PRS_DEFECTS__TEXT_LINE2;

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
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(ADDITIONAL_COMMENTS));
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
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(PRINT_NAME));
    }

    @Test
    public void verifyLocation() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(LOCATION));
    }

    @Test
    public void verifyLocationNumber() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(LOCATION_NUMBER));
    }

    @Test
    public void verifyDate() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains(DATE_OF_THE_TEST));
    }

    @Test
    public void verifyDefectAddedDuringTestText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DEFECT_ADDED_DURING_TEST_TEXT));
    }

    @Test
    public void verifyDoNotDriveUntilRepairedText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DO_NOT_DRIVE_UNTIL_REPAIRED_TEXT));
    }

    @Test
    public void verifyDangerousDefectsText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DANGEROUS_DEFECTS_TEXT_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(DANGEROUS_DEFECTS_TEXT_LINE2));
    }

    @Test
    public void verifyRepairImmediatelyText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REPAIR_IMMEDIATELY_TEXT));
    }

    @Test
    public void verifyMajorDefectsText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MAJOR_DEFECTS_TEXT_LINE1_1ST));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MAJOR_DEFECTS_TEXT_LINE1_2ND));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MAJOR_DEFECTS_TEXT_LINE2_2ND));
    }

    @Test
    public void verifyRepairAsSoonAsPossibleText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(REPAIR_AS_SOON_AS_POSSIBLE_TEXT));
    }

    @Test
    public void verifyMinorDefects() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MINOR_DEFECTS_TEXT_LINE1));
    }

    @Test
    public void verifyMonitorAndRepairIfNecessaryText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MONITOR_AND_REPAIR_IF_NECESSARY_TEXT));
    }

    @Test
    public void verifyAdvisoryDefectsText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ADVISORY_DEFECTS_TEXT_LINE1));
    }

    @Test
    public void verifyDefectsRectifiedAtTimeOfTestText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(DEFECTS_RECTIFIED_AT_TIME_OF_TEST_TEXT));
    }

    @Test
    public void verifyPRSDefectsText() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(PRS_DEFECTS__TEXT_LINE1));
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(PRS_DEFECTS__TEXT_LINE2));
    }

}
