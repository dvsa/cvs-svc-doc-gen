package htmlverification.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.MsvaPageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.MSVA30;
import uk.gov.dvsa.model.cvs.certificateData.AdditionalDefect;
import uk.gov.dvsa.model.cvs.certificateData.RequiredStandard;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;

public class MSVA30Test {
    protected HtmlGenerator htmlGenerator;
    protected MSVA30 testCertificate;
    protected MsvaPageObject msvaPageObject;

    public MSVA30Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws JsonProcessingException {
        testCertificate = CvsCertificateTestDataProvider.getMSVA30();
        String certHtmlIva30 = htmlGenerator.generate(testCertificate).get(0);
        msvaPageObject = new MsvaPageObject(certHtmlIva30);
    }

    @Test
    public void verifySignature() {
        String signatureElement = msvaPageObject.getSignatureImageSrc();
        String expected = testCertificate.getSignature().getFormattedImageData();

        assertEquals(expected, signatureElement);
    }

    @Test
    public void verifySerialNumber() {
        String serialNumber = msvaPageObject.getSerialNumber();
        assertEquals("Serial Number: ".concat(testCertificate.getMsvaData().getSerialNumber()), serialNumber);
    }

    @Test
    public void verifyVehicleZNumber() {
        String vehicleZNumber = msvaPageObject.getVehicleZNumber();
        assertEquals("Vehicle 'Z' No: ".concat(testCertificate.getMsvaData().getVehicleZNumber()), vehicleZNumber);
    }

    @Test
    public void verifyType() {
        String htmlType = msvaPageObject.getTestType();
        String type = testCertificate.getMsvaData().getType();
        assertEquals("Type: ".concat(type), htmlType);
    }

    @Test
    public void verifyMakeModel() {
        String makeModel = msvaPageObject.getMakeModel();
        String htmlMake = testCertificate.getMsvaData().getMake();
        String htmlModel = testCertificate.getMsvaData().getModel();
        assertEquals("Make/Model: ".concat(htmlMake + "/" + htmlModel), makeModel);
    }

    @Test
    public void verifyVin() {
        String vin = msvaPageObject.getVin();
        assertEquals("VIN/Chassis No: ".concat(testCertificate.getMsvaData().getVin()), vin);
    }

    @Test
    public void verifyDate() {
        String date = msvaPageObject.getDate();
        assertEquals("Date: ".concat(testCertificate.getMsvaData().getDate()), date);
    }

    @Test
    public void verifyReapplicationDate() {
        String retestDate = msvaPageObject.getReapplicationDate();
        assertEquals("Reapplication required by: 27/05/2024", retestDate);
    }

    @Test
    public void verifyTesterName() {
        String testerName = msvaPageObject.getTesterName();
        assertEquals("Name: ".concat(testCertificate.getMsvaData().getTesterName()), testerName);
    }

    @Test
    public void verifyStation() {
        String station = msvaPageObject.getStation();
        assertEquals("Station: ".concat(testCertificate.getMsvaData().getStation()), station);
    }

    @Test
    public void verifyRequiredStandards() {
        String requiredStandards = msvaPageObject.getRequiredStandards();
        RequiredStandard[] requiredStandardsArray = testCertificate.getMsvaData().getRequiredStandards();

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
        String additionalDefects = msvaPageObject.getAdditionalDefects();
        AdditionalDefect[] additionalDefectsArray = testCertificate.getMsvaData().getAdditionalDefects();

        String defectName = additionalDefectsArray[0].getDefectName();
        String defectNotes = additionalDefectsArray[0].getDefectNotes();

        assertEquals(defectName + " " + defectNotes, additionalDefects);
    }
}
