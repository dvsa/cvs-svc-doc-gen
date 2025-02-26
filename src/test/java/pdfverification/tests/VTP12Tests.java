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
    }
}
