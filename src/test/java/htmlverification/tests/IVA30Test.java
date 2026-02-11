package htmlverification.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.IvaPageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.IVA30;
import uk.gov.dvsa.model.cvs.certificateData.AdditionalDefect;
import uk.gov.dvsa.model.cvs.certificateData.RequiredStandard;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;

public class IVA30Test {
    protected HtmlGenerator htmlGenerator;
    protected IVA30 testCertificate;
    protected IvaPageObject ivaPageObject;

    public IVA30Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws JsonProcessingException {
        testCertificate = CvsCertificateTestDataProvider.getIVA30();
        String certHtmlIva30 = htmlGenerator.generate(testCertificate).get(0);
        ivaPageObject = new IvaPageObject(certHtmlIva30);
    }

    @Test
    public void verifySignature() {
        String signatureElement = ivaPageObject.getSignatureImageSrc();
        String expected = testCertificate.getSignature().getFormattedImageData();

        assertEquals(expected, signatureElement);
    }

    @Test
    public void verifySerialNumber() {
        String serialNumber = ivaPageObject.getSerialNumber();
        assertEquals("Serial Number: ".concat(testCertificate.getIvaData().getSerialNo()), serialNumber);
    }

    @Test
    public void verifyVehicleTrailerNumber() {
        String vehicleTrailerNumber = ivaPageObject.getVehicleTrailerNumber();
        assertEquals("Vehicle/Trailer NR No: ".concat(testCertificate.getIvaData().getVehicleTrailerNrNo()), vehicleTrailerNumber);
    }

    @Test
    public void verifyTestCategoryClass() {
        String vehicleCategory = ivaPageObject.getTestCategory();
        String testCatClass = testCertificate.getIvaData().getTestCategoryClass();
        String testCatBasicNormal = testCertificate.getIvaData().getTestCategoryBasicNormal();
        assertEquals("Test Category: ".concat(testCatClass + " (" + testCatBasicNormal + ")"), vehicleCategory);
    }

    @Test
    public void verifyMakeModel() {
        String makeModel = ivaPageObject.getMakeModel();
        String htmlMake = testCertificate.getIvaData().getMake();
        String htmlModel = testCertificate.getIvaData().getModel();
        assertEquals("Make/Model: ".concat(htmlMake + "/" + htmlModel), makeModel);
    }

    @Test
    public void verifyVin() {
        String vin = ivaPageObject.getVin();
        assertEquals("VIN/Chassis No: ".concat(testCertificate.getIvaData().getVin()), vin);
    }

    @Test
    public void verifyBodyType() {
        String bodyType = ivaPageObject.getBodyType();
        assertEquals("Body Type: ".concat(testCertificate.getIvaData().getBodyType()), bodyType);
    }

    @Test
    public void verifyDate() {
        String date = ivaPageObject.getDate();
        assertEquals("Date: ".concat(testCertificate.getIvaData().getDate()), date);
    }

    @Test
    public void verifyReapplicationDate() {
        String reapplicationDate = ivaPageObject.getReapplicationDate();
        assertEquals("Reapplication required by: 27/05/2024", reapplicationDate);
    }

    @Test
    public void verifyTesterName() {
        String testerName = ivaPageObject.getTesterName();
        assertEquals("Name: ".concat(testCertificate.getIvaData().getTesterName()), testerName);
    }

    @Test
    public void verifyStation() {
        String station = ivaPageObject.getStation();
        assertEquals("Station: ".concat(testCertificate.getIvaData().getStation()), station);
    }

    @Test
    public void verifyRequiredStandards() {
        String requiredStandards = ivaPageObject.getRequiredStandards();
        RequiredStandard[] requiredStandardsArray = testCertificate.getIvaData().getRequiredStandards();

        String sectionNo = requiredStandardsArray[0].getSectionNumber();
        String description = requiredStandardsArray[0].getSectionDescription();
        int rsNo = requiredStandardsArray[0].getRsNumber();
        String rs = requiredStandardsArray[0].getRequiredStandard();
        String addNotes = requiredStandardsArray[0].getAdditionalNotes();

        assertEquals(sectionNo + " - " + description + " RS" + rsNo + ": " + rs + " Additional Information: " + addNotes
                + " " + sectionNo + " - " + description + " RS" + rsNo + ": " + rs + " (PRS)" + " Additional Information: " + addNotes
                , requiredStandards);
    }

    @Test
    public void verifyAdditionalDefectsWhenNotApplicable() {
        String additionalDefects = ivaPageObject.getAdditionalDefects();
        AdditionalDefect[] additionalDefectsArray = testCertificate.getIvaData().getAdditionalDefects();

        String defectName = additionalDefectsArray[0].getDefectName();
        String defectNotes = additionalDefectsArray[0].getDefectNotes();

        assertEquals(defectName + " " + defectNotes, additionalDefects);
    }
}
