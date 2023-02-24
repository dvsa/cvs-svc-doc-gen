package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.MinistryPlate;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;

import static org.junit.Assert.*;

public class MinistryPlateTest {

    protected HtmlGenerator htmlGenerator;
    protected MinistryPlate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public MinistryPlateTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CertificateTestDataProvider.getMinistryPlate();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyVin() {
        String chassisNumber = certificatePageObject.getVin();
        assertEquals(testCertificate.getPlateData().getVin(), chassisNumber);
    }

    @Test
    public void verifyDtpNumber() {
        String dtpNumber = certificatePageObject.getDtpNumber();
        assertEquals(testCertificate.getPlateData().getDtpNumber(), dtpNumber);
    }

    @Test
    public void verifyPrimaryVrm() {
        String primaryVrm = certificatePageObject.getPrimaryVrm();
        assertEquals(testCertificate.getPlateData().getPrimaryVrm(), primaryVrm);
    }

    @Test
    public void verifyPlateSerialNumber() {
        String plateSerialNumber = certificatePageObject.getPlateSerialNumber();
        assertEquals(testCertificate.getPlateData().getPlateSerialNumber(), plateSerialNumber);
    }

    @Test
    public void verifyVariantNumber() {
        String variantNumber = certificatePageObject.getVariantNumber();
        assertEquals(testCertificate.getPlateData().getVariantNumber(), variantNumber);
    }

    @Test
    public void verifyApprovalTypeNumber() {
        String approvalTypeNumber = certificatePageObject.getApprovalTypeNumber();
        assertEquals(testCertificate.getPlateData().getApprovalTypeNumber(), approvalTypeNumber);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        String make = testCertificate.getPlateData().getMake();
        String model = testCertificate.getPlateData().getModel();
        assertEquals(make + "/" + model, makeAndModel);
    }

    @Test
    public void verifySpeedLimiterMrk() {
        String speedLimiterMrk = certificatePageObject.getSpeedLimiterMrk();
        assertEquals(testCertificate.getPlateData().getSpeedLimiterMrk(), speedLimiterMrk);
    }

    @Test
    public void verifyFunctionCode() {
        String functionCode = certificatePageObject.getFunctionCode();
        assertEquals(testCertificate.getPlateData().getFunctionCode(), functionCode);
    }

    @Test
    public void verifyRegnDate() {
        String regnDate = certificatePageObject.getRegnDate();
        assertEquals("12/12/2019", regnDate);
    }

    @Test
    public void verifyManufactureYear() {
        String manufactureYear = certificatePageObject.getManufactureYear();
        assertEquals(testCertificate.getPlateData().getManufactureYear(), manufactureYear);
    }

    @Test
    public void verifyGrossGbWeight() {
        String grossGbWeight = certificatePageObject.getGrossGbWeight();
        assertEquals(testCertificate.getPlateData().getGrossGbWeight(), grossGbWeight);
    }

    @Test
    public void verifyGrossEecWeight() {
        String grossEecWeight = certificatePageObject.getGrossEecWeight();
        assertEquals(testCertificate.getPlateData().getGrossEecWeight(), grossEecWeight);
    }

    @Test
    public void verifyGrossDesignWeight() {
        String grossDesignWeight = certificatePageObject.getGrossDesignWeight();
        assertEquals(testCertificate.getPlateData().getGrossDesignWeight(), grossDesignWeight);
    }

    @Test
    public void verifyTrainGbWeight() {
        String trainGbWeight = certificatePageObject.getTrainGbWeight();
        assertEquals(testCertificate.getPlateData().getTrainGbWeight(), trainGbWeight);
    }

    @Test
    public void verifyTrainEecWeight() {
        String trainEecWeight = certificatePageObject.getTrainEecWeight();
        assertEquals(testCertificate.getPlateData().getTrainEecWeight(), trainEecWeight);
    }

    @Test
    public void verifyTrainDesignWeight() {
        String trainDesignWeight = certificatePageObject.getTrainDesignWeight();
        assertEquals(testCertificate.getPlateData().getTrainDesignWeight(), trainDesignWeight);
    }

    @Test
    public void verifyMaxTrainGbWeight() {
        String maxTrainGbWeight = certificatePageObject.getMaxTrainGbWeight();
        assertEquals(testCertificate.getPlateData().getMaxTrainGbWeight(), maxTrainGbWeight);
    }

    @Test
    public void verifyMaxTrainEecWeight() {
        String maxTrainEecWeight = certificatePageObject.getMaxTrainEecWeight();
        assertEquals(testCertificate.getPlateData().getMaxTrainEecWeight(), maxTrainEecWeight);
    }

    @Test
    public void verifyMaxLoadOnCoupling() {
        String maxLoadOnCoupling = certificatePageObject.getMaxLoadOnCoupling();
        assertEquals(testCertificate.getPlateData().getMaxLoadOnCoupling(), maxLoadOnCoupling);
    }

    @Test
    public void verifyDimensionLength() {
        String dimensionLength = certificatePageObject.getDimensionLength();
        assertEquals(testCertificate.getPlateData().getDimensionLength(), dimensionLength);
    }

    @Test
    public void verifyDimensionWidth() {
        String dimensionWidth = certificatePageObject.getDimensionWidth();
        assertEquals(testCertificate.getPlateData().getDimensionWidth(), dimensionWidth);
    }

    @Test
    public void verifyFrontVehicleTo5thWheelCouplingMin() {
        String frontVehicleTo5thWheelCouplingMin = certificatePageObject.getFrontVehicleTo5thWheelCouplingMin();
        assertEquals(testCertificate.getPlateData().getFrontVehicleTo5thWheelCouplingMin(), frontVehicleTo5thWheelCouplingMin);
    }

    @Test
    public void verifyFrontVehicleTo5thWheelCouplingMax() {
        String frontVehicleTo5thWheelCouplingMax = certificatePageObject.getFrontVehicleTo5thWheelCouplingMax();
        assertEquals(testCertificate.getPlateData().getFrontVehicleTo5thWheelCouplingMax(), frontVehicleTo5thWheelCouplingMax);
    }

    @Test
    public void verifyCouplingCenterToRearTrlMax() {
        String couplingCenterToRearTrlMax = certificatePageObject.getCouplingCenterToRearTrlMax();
        assertEquals(testCertificate.getPlateData().getCouplingCenterToRearTrlMax(), couplingCenterToRearTrlMax);
    }

    @Test
    public void verifyCouplingCenterToRearTrlMin() {
        String couplingCenterToRearTrlMin = certificatePageObject.getCouplingCenterToRearTrlMin();
        assertEquals(testCertificate.getPlateData().getCouplingCenterToRearTrlMin(), couplingCenterToRearTrlMin);
    }

    @Test
    public void verifyPlateIssueDate() {
        String plateIssueDate = certificatePageObject.getPlateIssueDate();
        assertEquals("12/06/2020", plateIssueDate);
    }

    @Test
    public void verifyTyreUseCode() {
        String tyreUseCode = certificatePageObject.getTyreUseCode();
        assertEquals(testCertificate.getPlateData().getTyreUseCode(), tyreUseCode);
    }

    @Test
    public void verifyAxle1GbWeight() {
        String axle1GbWeight = certificatePageObject.getAxle1GbWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getWeights().getGbWeight(), axle1GbWeight);
    }

    @Test
    public void verifyAxle2GbWeight() {
        String axle2GbWeight = certificatePageObject.getAxle2GbWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getWeights().getGbWeight(), axle2GbWeight);
    }

    @Test
    public void verifyAxle3GbWeight() {
        String axle3GbWeight = certificatePageObject.getAxle3GbWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getWeights().getGbWeight(), axle3GbWeight);
    }

    @Test
    public void verifyAxle4GbWeight() {
        String axle4GbWeight = certificatePageObject.getAxle4GbWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getWeights().getGbWeight(), axle4GbWeight);
    }

    @Test
    public void verifyAxle1EecWeight() {
        String axle1EecWeight = certificatePageObject.getAxle1EecWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getWeights().getEecWeight(), axle1EecWeight);
    }

    @Test
    public void verifyAxle2EecWeight() {
        String axle2EecWeight = certificatePageObject.getAxle2EecWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getWeights().getEecWeight(), axle2EecWeight);
    }

    @Test
    public void verifyAxle3EecWeight() {
        String axle3EecWeight = certificatePageObject.getAxle3EecWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getWeights().getEecWeight(), axle3EecWeight);
    }

    @Test
    public void verifyAxle4EecWeight() {
        String axle4EecWeight = certificatePageObject.getAxle4EecWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getWeights().getEecWeight(), axle4EecWeight);
    }

    @Test
    public void verifyAxle1DesignWeight() {
        String axle1DesignWeight = certificatePageObject.getAxle1DesignWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getWeights().getDesignWeight(), axle1DesignWeight);
    }

    @Test
    public void verifyAxle2DesignWeight() {
        String axle2DesignWeight = certificatePageObject.getAxle2DesignWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getWeights().getDesignWeight(), axle2DesignWeight);
    }

    @Test
    public void verifyAxle3DesignWeight() {
        String axle3DesignWeight = certificatePageObject.getAxle3DesignWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getWeights().getDesignWeight(), axle3DesignWeight);
    }

    @Test
    public void verifyAxle4DesignWeight() {
        String axle4DesignWeight = certificatePageObject.getAxle4DesignWeight();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getWeights().getDesignWeight(), axle4DesignWeight);
    }

    @Test
    public void verifyAxle1TyreSize() {
        String axle1TyreSize = certificatePageObject.getAxle1TyreSize();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getTyres().getTyreSize(), axle1TyreSize);
    }

    @Test
    public void verifyAxle2TyreSize() {
        String axle2TyreSize = certificatePageObject.getAxle2TyreSize();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getTyres().getTyreSize(), axle2TyreSize);
    }

    @Test
    public void verifyAxle3TyreSize() {
        String axle3TyreSize = certificatePageObject.getAxle3TyreSize();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getTyres().getTyreSize(), axle3TyreSize);
    }

    @Test
    public void verifyAxle4TyreSize() {
        String axle4TyreSize = certificatePageObject.getAxle4TyreSize();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getTyres().getTyreSize(), axle4TyreSize);
    }

    @Test
    public void verifyAxle1PlyRating() {
        String axle1PlyRating = certificatePageObject.getAxle1PlyRating();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getTyres().getPlyRating(), axle1PlyRating);
    }

    @Test
    public void verifyAxle2PlyRating() {
        String axle2PlyRating = certificatePageObject.getAxle2PlyRating();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getTyres().getPlyRating(), axle2PlyRating);
    }

    @Test
    public void verifyAxle3PlyRating() {
        String axle3PlyRating = certificatePageObject.getAxle3PlyRating();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getTyres().getPlyRating(), axle3PlyRating);
    }

    @Test
    public void verifyAxle4PlyRating() {
        String axle4PlyRating = certificatePageObject.getAxle4PlyRating();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getTyres().getPlyRating(), axle4PlyRating);
    }

    @Test
    public void verifyAxle1FitmentCode() {
        String axle1FitmentCode = certificatePageObject.getAxle1FitmentCode();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle1().getTyres().getFitmentCode(), axle1FitmentCode);
    }

    @Test
    public void verifyAxle2FitmentCode() {
        String axle2FitmentCode = certificatePageObject.getAxle2FitmentCode();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle2().getTyres().getFitmentCode(), axle2FitmentCode);
    }

    @Test
    public void verifyAxle3FitmentCode() {
        String axle3FitmentCode = certificatePageObject.getAxle3FitmentCode();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle3().getTyres().getFitmentCode(), axle3FitmentCode);
    }

    @Test
    public void verifyAxle4FitmentCode() {
        String axle4FitmentCode = certificatePageObject.getAxle4FitmentCode();
        assertEquals(testCertificate.getPlateData().getAxles().getAxle4().getTyres().getFitmentCode(), axle4FitmentCode);
    }
}
