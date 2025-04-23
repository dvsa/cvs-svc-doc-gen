package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;

import htmlverification.framework.exception.HtmlElementNotFoundException;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTP30;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

public class VTP30Test {

    protected HtmlGenerator htmlGenerator;
    protected VTP30 testCertificate;
    protected CertificatePageObject certificatePageObject;

    public VTP30Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getVtp30();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
            String.format("(%s) %s", EU_NUMBER_SUMMARY_HEADER, "Fail"),
            resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(testCertificate.getFailData().getRawVin(), vin);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObject.getCountryOfRegistration();
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), country);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        assertEquals(
            String.format("%s, %s", testCertificate.getFailData().getMake(), testCertificate.getFailData().getModel()),
            makeAndModel
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObject.getVehicleCategory();
        assertEquals(testCertificate.getFailData().getVehicleEuClassification(), vehicleCategory);
    }

    @Test
    public void verifyAdvisories() {
        List<String> advisories = certificatePageObject.getDefectSummaryComponent().getAdvisories().eachText();

        assertEquals(1, advisories.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getMinorDefects().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyMajorDefects() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getMajorDefects().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDangerousDefects() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getDangerousDefects().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyPrsDefects() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getPrsDefects().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObject.getTestingOrganisationAndInspectorsName();
        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", text);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObject.getTestNumber();
        assertEquals("1806 8140 0628", testNumber);
    }

    @Test
    public void verifySignature() {
        String signatureElement = certificatePageObject.getSignatureImageSrc();
        String expected = testCertificate.getSignature().getFormattedImageData();

        assertEquals(expected, signatureElement);
    }

    @Test
    public void verifyTitle() {
        String titleText = certificatePageObject.getElement(".header__title").text();
        String expected = "Refusal of MOT test certificate";
        assertEquals(expected, titleText);
    }
    
    @Test(expected = HtmlElementNotFoundException.class)
    public void verifyReplacementInfoNotPresent() {
        certificatePageObject.getElementById("reissueInfo");
    }

    @Test
    public void verifyReplacementTitle() {
        testCertificate = CvsCertificateTestDataProvider.getReplacementVtp30();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String titleText = certificatePageObject.getElement(".header__title").text();
        String expected = "Replacement Refusal of MOT test certificate";
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyReplacementInfo() {
        testCertificate = CvsCertificateTestDataProvider.getReplacementVtp30();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String titleText = certificatePageObject.getElementById("reissueInfo").text();
        String expected = String.format("%s certificate issued by %s on %s",
            testCertificate.getReissue().getReason(),
            testCertificate.getReissue().getIssuer(),
            testCertificate.getReissue().getDate());
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyDocumentNameAndVersion() {
        String titleText = certificatePageObject.getElement(".footer__certificate-tag-text").text();
        String expected = testCertificate.getPresentedDocumentNameFail() + "/" + testCertificate.getVersionNumberFail();
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyCurrentOdomoter() {
        String actual = certificatePageObject.getElement("#mileage").text();
        String expected = "22,341 miles";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
