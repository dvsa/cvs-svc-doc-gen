package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTP20;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.mot.certificateData.MotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

public class VTP20Test {

    protected HtmlGenerator htmlGenerator;
    protected VTP20 testCertificate;
    protected CertificatePageObject certificatePageObject;

    public VTP20Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getVtp20();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
            String.format("(%s) %s", EU_NUMBER_SUMMARY_HEADER, PASS_WITH_DEFECTS_HEADER),
            resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(testCertificate.getData().getRawVin(), vin);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObject.getCountryOfRegistration();
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        assertEquals(
            String.format("%s, %s", testCertificate.getData().getMake(), testCertificate.getData().getModel()),
            makeAndModel
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObject.getVehicleCategory();
        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory);
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
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObject.getDateOfTheTest();
        assertEquals("12.11.2018", dateOfTheTest);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals("12.10.2018", expiryDate);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObject.getLocationOfTheTest();
        assertEquals("P12345, POPULAR GARAGES", location);
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
        String expected = "MOT test certificate (" + testCertificate.getTestType() + ")";
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyDocumentNameAndVersion() {
        String titleText = certificatePageObject.getElement(".footer__certificate-tag-text").text();
        String expected = testCertificate.getPresentedDocumentNamePass() + "/" + testCertificate.getVersionNumberPass();
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyCurrentOdomoter() {
        String actual = certificatePageObject.getElement("#mileage").text();
        String expected = "22,341 miles";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyOdomoterHistory() {
        String actual = certificatePageObject.getElement("#mileage-history").text();
        String expected = "120 km 01.02.2016 330 km 30.01.2017";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyOdometerHistoryMessageWhenNoHistoryExists() {
        testCertificate = CvsCertificateTestDataProvider.getVtp20WithNoOdometerHistory();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#no_data_message").text();
        String expected = "No data available";
        assertEquals(expected, actual);
    }
}