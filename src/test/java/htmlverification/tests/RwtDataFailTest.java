package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import uk.gov.dvsa.model.cvs.RwtCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RwtDataFailTest {
    protected HtmlGenerator htmlGenerator;
    protected RwtCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public RwtDataFailTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getRwtDataFail();
        List<String> certHtml = htmlGenerator.generate(testCertificate);
        certificatePageObject = new CertificatePageObject(certHtml.get(0));
        new PDFGenerationService(new ITextRenderer()).generate(certHtml);
    }

    @Test
    public void verifyCertTitle() {
        String dgvw = certificatePageObject.getCertTitle();
        String expectedTitle = "REFUSAL OF A ROADWORTHINESS INSPECTION CERTIFICATE";
        assertEquals(expectedTitle, dgvw);
    }

    @Test
    public void verifyDgvw() {
        String dgvw = certificatePageObject.getDgvw();
        String actualDgvw = testCertificate.getRwtData().getDgvw() + " kg";
        assertEquals(actualDgvw, dgvw);
    }

    @Test
    public void verifyWeight2() {
        String weight2 = certificatePageObject.getWeight2();
        String actualWeight2 = testCertificate.getRwtData().getWeight2() +" kg";
        assertEquals(actualWeight2, weight2);
    }

    @Test
    public void verifyVehicleNumber() {
        String vehicleNumber = certificatePageObject.getVehicleNumber();
        assertEquals(testCertificate.getRwtData().getVehicleNumber(), vehicleNumber);
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(testCertificate.getRwtData().getVin(), vin);
    }

    @Test
    public void verifyIssuersName() {
        String issuersName = certificatePageObject.getIssuersName();
        assertEquals(testCertificate.getRwtData().getIssuersName(), issuersName);
    }

    @Test
    public void verifyDateOfInspection() {
        String dateOfInspection = certificatePageObject.getDateOfInspection();
        assertEquals(testCertificate.getRwtData().getDateOfInspection(), dateOfInspection);
    }

    @Test
    public void verifyTestStationPNumber() {
        String testStationPNumber = certificatePageObject.getTestStationPNumber();
        assertEquals(testCertificate.getRwtData().getTestStationPNumber(), testStationPNumber);
    }


    @Test
    public void verifyDate() {
        String rwtDate = certificatePageObject.getRwtDate();
        assertEquals(testCertificate.getRwtData().getDate(), rwtDate);
    }

    @Test
    public void verifyDocNumber() {
        String rwtDocNumber = certificatePageObject.getRwtDocNumber();
        assertEquals("DVSA0480", rwtDocNumber);
    }

    @Test
    public void verifyDefects() {
        String defect0 = certificatePageObject.getRwtDefect(0);
        String defect1 = certificatePageObject.getRwtDefect(1);
        String defect2 = certificatePageObject.getRwtDefect(2);
        assertEquals(testCertificate.getRwtData().getDefects()[0], defect0);
        assertEquals(testCertificate.getRwtData().getDefects()[1], defect1);
        assertEquals(testCertificate.getRwtData().getDefects()[2], defect2);
    }
}
