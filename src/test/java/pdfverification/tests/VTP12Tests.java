package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

public class VTP12Tests extends AbandonedTests {

    public VTP12Tests() {
        this.testCertificate = CvsCertificateTestDataProvider.getVTP12();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();

        super.DYNAMIC_TITLE_SECTION = "Examination (VTP12)";
        super.REGULATION_LINE1 = "Regulation 13 of the Motor Vehicles (Tests) ";
        super.REGULATION_LINE2 = "Regulations 1981 as amended";
        super.VEHICLE_TYPE_TEXT_LINE1 = "In respect of the public service vehicle with registration number / chassis serial number :";
        super.VEHICLE_TYPE_TEXT_LINE2 = "";
        super.VIN = "P O I U Y T R E W Q 0 1 2 3 0 1 0 9 5 6 7 8 9 1";
        super.REASONS_FOR_REFUSAL_LINE1 = "Reason 1 exists VTP12";
        super.REASONS_FOR_REFUSAL_LINE2 = "Reason 1 exists VTP12";
        super.ROLLING_FOOTER_LEFT = "VTP12 (DVSA0453)";
        super.ADDITIONAL_COMMENTS = "additional comments VTP12";
        super.ROLLING_HEADER_LEFT = "VTP12";
        super.ROLLING_HEADER_RIGHT = "Acceptance of a Public Service Vehicle for Examination";
        super.PRINT_NAME = "fake tester";
        super.LOCATION = "fake12312312";
        super.LOCATION_NUMBER = "fake12312312";
        super.DATE_OF_THE_TEST = "01.02.2024";
        super.SECTION_TEXT = "having been submitted for an examination under Section 45 of the Road Traffic Act 1988, it is";
        super.DEFECT_ADDED_DURING_TEST_TEXT = "Defects added during test:";
        super.DO_NOT_DRIVE_UNTIL_REPAIRED_TEXT = "Do not drive until repaired (dangerous defects)";
        super.DANGEROUS_DEFECTS_TEXT_LINE1 = "18.1.a.ii A driver’s seat: so insecure or in such a condition that it could cause the driver";
        super.DANGEROUS_DEFECTS_TEXT_LINE2 = "to lose control of the vehicle.";
        super.REPAIR_IMMEDIATELY_TEXT = "Repair immediately (major defects)";
        super.MAJOR_DEFECTS_TEXT_LINE1_1ST = "3.1.a Obligatory Seat Belt: missing.";
        super.MAJOR_DEFECTS_TEXT_LINE1_2ND = "62.1.a.ii Reflectors, conspicuity markings and/or rear markers: missing, incorrectly";
        super.MAJOR_DEFECTS_TEXT_LINE2_2ND = "positioned and red colour is reflected to the front or white to the rear.";
        super.REPAIR_AS_SOON_AS_POSSIBLE_TEXT = "Repair as soon as possible (minor defects)";
        super.MINOR_DEFECTS_TEXT_LINE1 = "8.1.d.i A tyre: rubbing on any part of the vehicle.";
        super.MONITOR_AND_REPAIR_IF_NECESSARY_TEXT = "Monitor and repair if necessary (advisories)";
        super.ADVISORY_DEFECTS_TEXT_LINE1 = "67.2 European checked on Dipped Beam: The beam image contains";
        super.DEFECTS_RECTIFIED_AT_TIME_OF_TEST_TEXT = "Defects rectified at time of test";
        super.PRS_DEFECTS__TEXT_LINE1 = "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a";
        super.PRS_DEFECTS__TEXT_LINE2 = "combination of tyres with structures which are not shown as acceptable in the table.";
    }
}
