package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CertificateTestDataProvider;
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
        testCertificate = CertificateTestDataProvider.getAdrPass();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyChassisNumber() {
        String chassisNumber = certificatePageObject.getChassisNumber();
        assertEquals(testCertificate.getAdrData().getChasisNumber(), chassisNumber);
    }

    @Test
    public void verifyApplicantDetailsName() {
        String applicantDetailsName = certificatePageObject.getApplicantDetailsName();
        assertEquals(testCertificate.getAdrData().getApplicantDetails().getName(), applicantDetailsName);
    }

    @Test
    public void verifyApplicantDetailsStreet() {
        String applicantDetailsStreet = certificatePageObject.getApplicantDetailsStreet();
        assertEquals(testCertificate.getAdrData().getApplicantDetails().getStreet(), applicantDetailsStreet);
    }

    @Test
    public void verifyVehicleType() {
        String vehicleType = certificatePageObject.getVehicleType();
        assertEquals(testCertificate.getAdrData().getVehicleType(), vehicleType);
    }

    @Test
    public void verifyFormattedPermittedDangerousGoods() {
        String formattedPermittedDangerousGoods = certificatePageObject.getFormattedPermittedDangerousGoods();
        assertEquals(testCertificate.getAdrData().getFormattedPermittedDangerousGoods().trim(), formattedPermittedDangerousGoods.trim());
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

    @Test
    public void verifyNotes() {
        String notes = certificatePageObject.getNotes();
        assertEquals(testCertificate.getAdrData().getNotes(), notes);
    }

    @Test
    public void verifyTestTypeDate() {
        String testTypeDate = certificatePageObject.getTestTypeDate();
        assertEquals(testCertificate.getAdrData().getTestTypeDate(), testTypeDate);
    }

    @Test
    public void verifyAtfNameAtfPNumber() {
        String atfNameAtfPNumber = certificatePageObject.getAtfNameAtfPNumber();
        assertEquals(testCertificate.getAdrData().getAtfNameAtfPNumber(), atfNameAtfPNumber);
    }

    @Test
    public void verifyAdrCertificateNotes() {
        String dangerousGoodsFooter = certificatePageObject.getDangerousGoodsFooter();
        String adrCertificateNotes = testCertificate.getAdrData().getAdrCertificateNotes();
        assertTrue(dangerousGoodsFooter.contains(adrCertificateNotes));
    }

    @Test
    public void verifyProductListUnNo() {
        String dangerousGoodsFooter = certificatePageObject.getDangerousGoodsFooter();
        String productListUnNo = testCertificate.getAdrData().getTankStatement().getProductListUnNo();
        assertTrue(dangerousGoodsFooter.contains(productListUnNo));
    }

    @Test
    public void verifyBatteryListNumber() {
        String dangerousGoodsFooter = certificatePageObject.getDangerousGoodsFooter();
        String batteryListNumber = testCertificate.getAdrData().getBatteryListNumber();
        assertTrue(dangerousGoodsFooter.contains("AS PER BATTERY-ELEMENT LIST NO " + batteryListNumber + " ATTACHED"));
    }

    @Test
    public void verifyPermittedDangerousGoodsMessage() {
        String dangerousGoodsFooter = certificatePageObject.getDangerousGoodsFooter();
        assertTrue(dangerousGoodsFooter.contains("THIS VEHICLE IS CERTIFIED FOR THE CARRIAGE OF DANGEROUS GOODS IN CONJUNCTION "
            +"WITH SUITABLY CERTIFIED TANK OR BATTERY CONTAINERS OR SUITABLY CONSTRUCTED EXPLOSIVE CONTAINERS AS APPROPRIATE"));
    }

}
