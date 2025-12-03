package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.MinistryPlate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class MinistryPlateTests {
    protected HtmlGenerator htmlGenerator;
    protected PDFGenerationService pdfGenerationService;
    protected MinistryPlate testCertificate;
    protected PDFParser pdfParser;
    protected PdfReader pdfReader;
    protected byte[] pdfData;

    static String PLATE_SERIAL_NUMBER = "12345";
    static String DTP_REF_NUMBER = "DTPNUM";
    static String VRM = "BBB333";
    static String VIN = "MMMMMMMMMMMMMMMMMMMMW";
    static String VARIANT_NUMBER_PART1 = "MWMMMMMMMMMMMMMMMM";
    static String VARIANT_NUMBER_PART2 = "MWMMMMMMMMMMMMMMW";
    static String TYPE_APPROVAL_NUMBER = "12345";
    static String MAKE_AND_MODEL = "Ford/Focus";
    static String SPEED_LIMITER_EXEMPT = "Yes";
    static String FUNCTION_CODE = "2AT";
    static String YEAR_OF_ORIGINAL_REGISTRATION = "12/12/2019";
    static String YEAR_OF_MANUFACTURE = "2018";
    static String LENGTH = "5600";
    static String WIDTH = "8700";
    static String GROSS_WEIGHT_NOT_TO_BE_EXCEEDED = "2987";
    static String GROSS_WEIGHT_MAX_PERMITTED_WEIGHT = "2876";
    static String GROSS_WEIGHT_DESIGN_WEIGHT = "2765";
    static String COUPLING_FOREMOST_MAX = "9845";
    static String COUPLING_FOREMOST_MIN = "9999";
    static String TRAIN_WEIGHT_NOT_TO_BE_EXCEEDED = "2764";
    static String TRAIN_WEIGHT_MAX_PERMITTED_WEIGHT = "2466";
    static String TRAIN_WEIGHT_DESIGN_WEIGHT = "4452";
    static String COUPLING_REARMOST_MAX = "4012";
    static String COUPLING_REARMOST_MIN = "4013";
    static String MAX_TRAIN_WEIGHT_NOT_TO_BE_EXCEEDED = "2233";
    static String MAX_TRAIN_WEIGHT_MAX_PERMITTED_WEIGHT = "1234";
    static String AXLES_1_WEIGHT_NOT_TO_BE_EXCEEDED = "1230";
    static String AXLES_1_WEIGHT_MAX_PERMITTED_WEIGHT = "1245";
    static String AXLES_1_TYRE_SIZE = "205/45/R17";
    static String AXLES_1_LOAD_RATING = "152/148";
    static String AXLES_1_WEIGHT_DESIGN_WEIGHT = "5522";
    static String AXLES_2_WEIGHT_NOT_TO_BE_EXCEEDED = "1231";
    static String AXLES_2_WEIGHT_MAX_PERMITTED_WEIGHT = "1246";
    static String AXLES_2_WEIGHT_DESIGN_WEIGHT = "5523";
    static String AXLES_2_TYRE_SIZE = "205/45/R18";
    static String AXLES_2_LOAD_RATING = "152/147";
    static String AXLES_3_WEIGHT_NOT_TO_BE_EXCEEDED = "1232";
    static String AXLES_3_WEIGHT_MAX_PERMITTED_WEIGHT = "1247";
    static String AXLES_3_WEIGHT_DESIGN_WEIGHT = "5524";
    static String AXLES_3_TYRE_SIZE = "205/45/R19";
    static String AXLES_3_LOAD_RATING = "152/146";
    static String AXLES_4_WEIGHT_NOT_TO_BE_EXCEEDED = "1233";
    static String AXLES_4_WEIGHT_MAX_PERMITTED_WEIGHT = "1247";
    static String AXLES_4_WEIGHT_DESIGN_WEIGHT = "5525";
    static String AXLES_4_TYRE_SIZE = "205/45/R20";
    static String AXLES_4_LOAD_RATING = "152/145";
    static String MAX_KINGPIN_LOAD = "2500";
    static String TYPE_USE_CONDITIONS_APPLICABLE_TO_VEHICLE = "2B";
    static String VEHICLE_TESTING_STATION_NO = "H999";
    static String DATE_OF_ISSUE = "12/06/2020";


    public MinistryPlateTests() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(CvsCertificateTestDataProvider.getMinistryPlate()));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyVin() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), VIN));
    }

    @Test
    public void verifyDtpNumber() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), DTP_REF_NUMBER));
    }

    @Test
    public void verifyPrimaryVrm() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), VRM));
    }

    @Test
    public void verifyPlateSerialNumber() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), PLATE_SERIAL_NUMBER));
    }

    @Test
    public void verifyVariantNumber() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), VARIANT_NUMBER_PART1));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), VARIANT_NUMBER_PART2));
    }

    @Test
    public void verifyApprovalTypeNumber() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), TYPE_APPROVAL_NUMBER));
    }

    @Test
    public void verifyMakeAndModel() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), MAKE_AND_MODEL));
    }

    @Test
    public void verifySpeedLimiterMrk() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), SPEED_LIMITER_EXEMPT));
    }

    @Test
    public void verifyFunctionCode() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), FUNCTION_CODE));
    }

    @Test
    public void verifyRegnDate() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), YEAR_OF_ORIGINAL_REGISTRATION));
    }

    @Test
    public void verifyManufactureYear() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), YEAR_OF_MANUFACTURE));
    }

    @Test
    public void verifyGrossGbWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), GROSS_WEIGHT_NOT_TO_BE_EXCEEDED));
    }

    @Test
    public void verifyGrossEecWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), GROSS_WEIGHT_MAX_PERMITTED_WEIGHT));
    }

    @Test
    public void verifyGrossDesignWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), GROSS_WEIGHT_DESIGN_WEIGHT));
    }

    @Test
    public void verifyTrainGbWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), TRAIN_WEIGHT_NOT_TO_BE_EXCEEDED));
    }

    @Test
    public void verifyTrainEecWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), TRAIN_WEIGHT_MAX_PERMITTED_WEIGHT));
    }

    @Test
    public void verifyTrainDesignWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), TRAIN_WEIGHT_DESIGN_WEIGHT));
    }

    @Test
    public void verifyMaxTrainGbWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), MAX_TRAIN_WEIGHT_NOT_TO_BE_EXCEEDED));
    }

    @Test
    public void verifyMaxTrainEecWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), MAX_TRAIN_WEIGHT_MAX_PERMITTED_WEIGHT));
    }

    @Test
    public void verifyMaxLoadOnCoupling() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), MAX_KINGPIN_LOAD));
    }

    @Test
    public void verifyDimensionLength() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), LENGTH));
    }

    @Test
    public void verifyDimensionWidth() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), WIDTH));
    }

    @Test
    public void verifyFrontVehicleTo5thWheelCouplingMin() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), COUPLING_FOREMOST_MIN));
    }

    @Test
    public void verifyFrontVehicleTo5thWheelCouplingMax() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), COUPLING_FOREMOST_MAX));
    }

    @Test
    public void verifyCouplingCenterToRearTrlMax() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), COUPLING_REARMOST_MAX));
    }

    @Test
    public void verifyCouplingCenterToRearTrlMin() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), COUPLING_REARMOST_MIN));
    }

    @Test
    public void verifyPlateIssueDate() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), DATE_OF_ISSUE));
    }

    @Test
    public void verifyPlateIssueDateRight() throws IOException {
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), "[" + DATE_OF_ISSUE + "]"));
    }

    @Test
    public void verifyTyreUseCode() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), TYPE_USE_CONDITIONS_APPLICABLE_TO_VEHICLE));
    }

    @Test
    public void verifyAxleGbWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_1_WEIGHT_NOT_TO_BE_EXCEEDED));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_2_WEIGHT_NOT_TO_BE_EXCEEDED));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_3_WEIGHT_NOT_TO_BE_EXCEEDED));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_4_WEIGHT_NOT_TO_BE_EXCEEDED));
    }

    @Test
    public void verifyAxleEecWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_1_WEIGHT_MAX_PERMITTED_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_2_WEIGHT_MAX_PERMITTED_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_3_WEIGHT_MAX_PERMITTED_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_4_WEIGHT_MAX_PERMITTED_WEIGHT));
    }

    @Test
    public void verifyAxleDesignWeight() throws IOException {
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_1_WEIGHT_DESIGN_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_2_WEIGHT_DESIGN_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_3_WEIGHT_DESIGN_WEIGHT));
        assertEquals(2, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_4_WEIGHT_DESIGN_WEIGHT));
    }

    @Test
    public void verifyAxleTyreSize() throws IOException {
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_1_TYRE_SIZE));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_2_TYRE_SIZE));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_3_TYRE_SIZE));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_4_TYRE_SIZE));
    }

    @Test
    public void verifyAxlePlyRating() throws IOException {
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_1_LOAD_RATING));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_2_LOAD_RATING));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_3_LOAD_RATING));
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), AXLES_4_LOAD_RATING));
    }

    @Test
    public void verifyVehicleTestingStation() throws IOException {
        assertEquals(1, StringUtils.countMatches(pdfParser.getRawText(pdfReader, 1), VEHICLE_TESTING_STATION_NO));
    }
}
