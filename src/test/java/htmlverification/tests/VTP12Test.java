package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import uk.gov.dvsa.service.HtmlGenerator;
import java.io.IOException;

public class VTP12Test extends AbandonedTest {

    public VTP12Test() {
        super.DYNAMIC_TITLE_SECTION = "Public Service Vehicle for Examination";
        super.REGULATION_TEXT = "Regulation 13 of the Motor Vehicles (Tests) Regulations 1981 as amended";
        super.VEHICLE_TYPE_TEXT_LINE = "In respect of the public service vehicle with registration number / chassis serial number :";
        super.FORM_NUMBER = "0453";
        super.SECTION_REF_TEXT = "45";
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getVTP12();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        super.certificatePageObject = new CertificatePageObject(certHtml);
    }
}