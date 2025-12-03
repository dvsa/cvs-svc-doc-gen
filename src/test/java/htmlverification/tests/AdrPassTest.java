package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.AdrPassCertificate;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdrPassTest {

    protected HtmlGenerator htmlGenerator;
    protected AdrPassCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public AdrPassTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getAdrPass();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyChassisNumber() {
        String chassisNumber = certificatePageObject.getVin();
        assertEquals(testCertificate.getAdrData().getVin(), chassisNumber);
    }

    @Test
    public void verifyApplicantDetailsName() {
        String applicantDetailsName = certificatePageObject.getApplicantDetailsName();
        assertEquals(testCertificate.getAdrData().getApplicantDetails().getName(), applicantDetailsName);
    }

    @Test
    public void verifyVehicleType() {
        String vehicleType = certificatePageObject.getAdrVehicleType();
        assertEquals(testCertificate.getAdrData().getAdrVehicleType(), vehicleType);
    }

    @Test
    public void verifyWeight() {
        String weight = certificatePageObject.getWeight();
        assertEquals(testCertificate.getAdrData().getWeight(), weight);
    }

    @Test
    public void verifyTankManufacturer() {
        String tankManufacturer = certificatePageObject.getTankManufacturer();
        assertEquals(testCertificate.getAdrData().getTankManufacturer(), tankManufacturer);
    }

    @Test
    public void verifyTc2InitApprovalNo() {
        String tc2InitApprovalNo = certificatePageObject.getTc2InitApprovalNo();
        assertEquals(testCertificate.getAdrData().getTc2InitApprovalNo(), tc2InitApprovalNo);
    }

    @Test
    public void verifyTankManufactureSerialNo() {
        String tankManufactureSerialNo = certificatePageObject.getTankManufactureSerialNo();
        assertEquals(testCertificate.getAdrData().getTankManufactureSerialNo(), tankManufactureSerialNo);
    }

    @Test
    public void verifyYearOfManufacture() {
        String yearOfManufacture = certificatePageObject.getYearOfManufacture();
        assertEquals(testCertificate.getAdrData().getYearOfManufacture(), yearOfManufacture);
    }

    @Test
    public void verifySpecialProvisions() {
        String specialProvisions = certificatePageObject.getSpecialProvisions();
        assertEquals(testCertificate.getAdrData().getSpecialProvisions(), specialProvisions);
    }

    @Test
    public void verifyTankCode() {
        String tankCode = certificatePageObject.getTankCode();
        assertEquals(testCertificate.getAdrData().getTankCode(), tankCode);
    }
}
