package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

public class VTG12Tests extends AbandonedTests{

    public VTG12Tests() {
        super.testCertificate = CvsCertificateTestDataProvider.getVTG12();
        super.htmlGenerator = new HtmlGenerator(new Handlebars());
        super.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        super.pdfParser = new PDFParser();

        super.DYNAMIC_TITLE_SECTION = "Examination (VTG12)";
        super.REGULATION_LINE1 = "Regulations 7 and 8 of the Goods Vehicles (Plating";
        super.REGULATION_LINE2 = "and Testing) Regulations 1988 as Amended";
        super.VEHICLE_TYPE_TEXT_LINE1 = "In respect of the goods vehicle with registration number / chassis serial number / trailer";
        super.VEHICLE_TYPE_TEXT_LINE2 = "identification mark :";
        super.VIN = "P O I U Y T R E W Q 0 1 2 3 0 1 0 9 5 6 7 8 9 1";
        super.REASONS_FOR_REFUSAL_LINE1 = "Reason 1 exists VTG12";
        super.REASONS_FOR_REFUSAL_LINE2 = "Reason 2 exists VTG12";
        super.ROLLING_FOOTER_LEFT = "VTG12 (DVSA0440)";
        super.ADDITIONAL_COMMENTS = "additional comments VTG12";
        super.ROLLING_HEADER_LEFT = "VTG12";
        super.ROLLING_HEADER_RIGHT = "Acceptance of a Goods Vehicle for Examination";
        super.PRINT_NAME = "fake tester";
        super.LOCATION = "fake12312312";
        super.LOCATION_NUMBER = "fake12312312";
        super.DATE_OF_THE_TEST = "01.02.2024";
        super.SECTION_TEXT = "having been submitted for an examination under Section 49 and 51 of the Road Traffic Act";
    }
}
