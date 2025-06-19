package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsPsvPassBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

import java.io.IOException;
import java.util.List;

public class CvsPsvPassBilingualTest {

    protected HtmlGenerator htmlGenerator;
    protected CvsPsvPassBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTP20;
    protected CertificatePageObject certificatePageObjectVTP20W;

    public CvsPsvPassBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsPsvPassBilingual();

        String certHtmlVTP20 = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTP20W = htmlGenerator.generate(testCertificate).get(1);

        certificatePageObjectVTP20 = new CertificatePageObject(certHtmlVTP20);
        certificatePageObjectVTP20W = new CertificatePageObject(certHtmlVTP20W);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTP20.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTP20W.getDefectSummaryComponent().getResultNameItem().text();

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
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTP20.getCountryOfRegistration();
        String country2 = certificatePageObjectVTP20W.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country2);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTP20.getMakeAndModel();
        String makeAndModel2 = certificatePageObjectVTP20W.getMakeAndModel();

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
        String vehicleCategory = certificatePageObjectVTP20.getVehicleCategory();
        String vehicleCategory2 = certificatePageObjectVTP20W.getVehicleCategory();

        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory);
        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory2);
    }

    @Test
    public void verifyMileage() {
        String mileage = certificatePageObjectVTP20.getMileage();
        String mileage2 = certificatePageObjectVTP20W.getMileage();

        assertEquals(testCertificate.getData().getFormattedCurrentOdometer(), mileage);
//        assertEquals(testCertificate.getData().getFormattedCurrentOdometer(), mileage2);
    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> advisoryDefects = certificatePageObjectVTP20.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTP20.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyAdvisoryDefectsWelsh() {
        List<String> advisoryDefects = certificatePageObjectVTP20W.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTP20W.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObjectVTP20.getDateOfTheTest();
        String dateOfTheTest2 = certificatePageObjectVTP20W.getDateOfTheTest();

        assertEquals("12.11.2018", dateOfTheTest);
        assertEquals("12.11.2018", dateOfTheTest2);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObjectVTP20.getLocationOfTheTest();
        String location2 = certificatePageObjectVTP20W.getLocationOfTheTest();

        assertEquals("P12345, POPULAR GARAGES", location);
        assertEquals("P12345, POPULAR GARAGES", location2);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObjectVTP20.getTestingOrganisationAndInspectorsName();
        String text2 = certificatePageObjectVTP20W.getTestingOrganisationAndInspectorsName();

        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", text);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", text2);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTP20.getTestNumber();
        String testNumber2 = certificatePageObjectVTP20W.getTestNumber();

        assertEquals("1806 8140 0628", testNumber);
        assertEquals("1806 8140 0628", testNumber2);
    }

    @Test
    public void verifyExpiryDateOnPassIsPresent() {
        String expiryDate = certificatePageObjectVTP20.getExpiryDate();
        String expiryDate2 = certificatePageObjectVTP20W.getExpiryDate();

        assertEquals("12.10.2018", expiryDate);
        assertEquals("12.10.2018", expiryDate2);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObjectVTP20W.getRecallsHeader();
        String contentText = certificatePageObjectVTP20W.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObjectVTP20.getRecallsHeader();
        String contentText = certificatePageObjectVTP20.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
