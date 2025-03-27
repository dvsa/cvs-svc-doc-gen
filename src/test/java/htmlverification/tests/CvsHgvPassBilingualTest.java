package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsHgvPassBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

public class CvsHgvPassBilingualTest {

    protected HtmlGenerator htmlGenerator;
    protected CvsHgvPassBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTG5;
    protected CertificatePageObject certificatePageObjectVTG5W;

    public CvsHgvPassBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsHgvPassBilingual();

        String certHtmlVTG5 = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTV5W = htmlGenerator.generate(testCertificate).get(1);

        certificatePageObjectVTG5 = new CertificatePageObject(certHtmlVTG5);
        certificatePageObjectVTG5W = new CertificatePageObject(certHtmlVTV5W);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTG5.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTG5W.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER),
                resultName
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER_WELSH),
                resultNameWelsh
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObjectVTG5.getVin();
        String vin2 = certificatePageObjectVTG5W.getVin();

        assertEquals(testCertificate.getData().getRawVin(), vin);
        assertEquals(testCertificate.getData().getRawVin(), vin2);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTG5.getCountryOfRegistration();
        String country2 = certificatePageObjectVTG5W.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country2);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTG5.getMakeAndModel();
        String makeAndModel2 = certificatePageObjectVTG5W.getMakeAndModel();

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",",  testCertificate.getData().getModel()),
                makeAndModel2
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObjectVTG5.getVehicleCategory();
        String vehicleCategory2 = certificatePageObjectVTG5W.getVehicleCategory();

        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory);
        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory2);
    }

    @Test
    public void verifyMileage() {
        String mileage = certificatePageObjectVTG5.getMileage();
        String mileage2 = certificatePageObjectVTG5W.getMileage();

        assertEquals(testCertificate.getData().getFormattedCurrentOdometer(), mileage);
        assertEquals("20,000 cilometrau", mileage2);
    }

    @Test
    public void verifyAdvisories() {
        List<String> advisories = certificatePageObjectVTG5.getDefectSummaryComponent().getAdvisories().eachText();

        assertEquals(1, advisories.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTG5.getDefectSummaryComponent().getMinorDefects().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyAdvisoriesWelsh() {
        List<String> advisories = certificatePageObjectVTG5W.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();

        assertEquals(1, advisories.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTG5W.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();

        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObjectVTG5.getDateOfTheTest();
        String dateOfTheTest2 = certificatePageObjectVTG5W.getDateOfTheTest();

        assertEquals("22.08.2023", dateOfTheTest);
        assertEquals("22.08.2023", dateOfTheTest2);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObjectVTG5.getLocationOfTheTest();
        String location2 = certificatePageObjectVTG5W.getLocationOfTheTest();

        assertEquals("P12345, TEST STATION NAME", location);
        assertEquals("P12345, TEST STATION NAME", location2);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObjectVTG5.getTestingOrganisationAndInspectorsName();
        String text2 = certificatePageObjectVTG5W.getTestingOrganisationAndInspectorsName();

        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY TESTER NAME", text);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU TESTER NAME", text2);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTG5.getTestNumber();
        String testNumber2 = certificatePageObjectVTG5W.getTestNumber();

        assertEquals("X01X00001", testNumber);
        assertEquals("X01X00001", testNumber2);
    }

    @Test
    public void verifyExpiryDateOnPassIsPresent() {
        String expiryDate = certificatePageObjectVTG5.getExpiryDate();
        String expiryDate2 = certificatePageObjectVTG5W.getExpiryDate();

        assertEquals("31.08.2024", expiryDate);
        assertEquals("31.08.2024", expiryDate2);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObjectVTG5W.getRecallsHeader();
        String contentText = certificatePageObjectVTG5W.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObjectVTG5.getRecallsHeader();
        String contentText = certificatePageObjectVTG5.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
