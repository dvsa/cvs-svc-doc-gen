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

public class RwtDataTest {
    protected HtmlGenerator htmlGenerator;
    protected RwtCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public RwtDataTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getRwtData();
        List<String> certHtml = htmlGenerator.generate(testCertificate);
        certificatePageObject = new CertificatePageObject(certHtml.get(0));
        new PDFGenerationService(new ITextRenderer()).generate(certHtml);
    }

    @Test
    public void verifyCertTitle() {
        String dgvw = certificatePageObject.getCertTitle();
        String expectedTitle = "ROADWORTHINESS INSPECTION CERTIFICATE";
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
    public void verifyDocumentNumber() {
        String documentNumber = certificatePageObject.getDocumentNumber();
        assertEquals(testCertificate.getRwtData().getDocumentNumber(), documentNumber);
    }

    @Test
    public void verifyDate() {
        String rwtDate = certificatePageObject.getRwtDate();
        assertEquals(testCertificate.getRwtData().getDate(), rwtDate);
    }

    @Test
    public void verifyDocNumber() {
        String rwtDocNumber = certificatePageObject.getRwtDocNumber();
        assertEquals("DVSA0479", rwtDocNumber);
    }

    @Test
    public void verifyDocVersion() {
        String rwtDocVersion = certificatePageObject.getRwtDocVersion();
        assertEquals("v 1.2 May 2025", rwtDocVersion);
    }
}
