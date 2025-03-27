package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import uk.gov.dvsa.service.HtmlGenerator;
import java.io.IOException;

public class VTG12Test extends AbandonedTest {

    public VTG12Test() {
        super.DYNAMIC_TITLE_SECTION = "Goods Vehicle for Examination";
        super.REGULATION_TEXT = "Regulations 7 and 8 of the Goods Vehicles (Plating and Testing) Regulations 1988 as Amended";
        super.VEHICLE_TYPE_TEXT_LINE = "In respect of the goods vehicle with registration number / chassis serial number / trailer identification mark :";
        super.FORM_NUMBER = "0440";
        super.SECTION_REF_TEXT = "49 and 51";
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getVTG12();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        super.certificatePageObject = new CertificatePageObject(certHtml);
    }
}