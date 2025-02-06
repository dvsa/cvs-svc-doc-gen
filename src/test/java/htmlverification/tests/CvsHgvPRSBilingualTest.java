package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsHgvPRSBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateData.FAILED_SUMMARY_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateDataWelsh.FAILED_SUMMARY_HEADER_WELSH;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

public class CvsHgvPRSBilingualTest {

    protected HtmlGenerator htmlGenerator;
    protected CvsHgvPRSBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTG5;
    protected CertificatePageObject certificatePageObjectVTG30;

    protected CertificatePageObject certificatePageObjectVTG5W;
    protected CertificatePageObject certificatePageObjectVTG30W;

    public CvsHgvPRSBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsHgvPRSBilingual();

        String certHtmlVTG5 = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTG30 = htmlGenerator.generate(testCertificate).get(1);
        String certHtmlVTV5W = htmlGenerator.generate(testCertificate).get(2);
        String certHtmlVTG30W = htmlGenerator.generate(testCertificate).get(3);

        certificatePageObjectVTG5 = new CertificatePageObject(certHtmlVTG5);
        certificatePageObjectVTG30 = new CertificatePageObject(certHtmlVTG30);
        certificatePageObjectVTG5W = new CertificatePageObject(certHtmlVTV5W);
        certificatePageObjectVTG30W = new CertificatePageObject(certHtmlVTG30W);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTG5.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameFail = certificatePageObjectVTG30.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTG5W.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameFailWelsh = certificatePageObjectVTG30W.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER),
                resultName
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER),
                resultNameFail
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER_WELSH),
                resultNameWelsh
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER_WELSH),
                resultNameFailWelsh
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObjectVTG5.getVin();
        String vinFail = certificatePageObjectVTG30.getVin();
        String vinWelsh = certificatePageObjectVTG5W.getVin();
        String vinFailWelsh = certificatePageObjectVTG30W.getVin();

        assertEquals(testCertificate.getData().getRawVin(), vin);
        assertEquals(testCertificate.getFailData().getRawVin(), vinFail);
        assertEquals(testCertificate.getData().getRawVin(), vinWelsh);
        assertEquals(testCertificate.getFailData().getRawVin(), vinFailWelsh);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTG5.getCountryOfRegistration();
        String countryFail = certificatePageObjectVTG30.getCountryOfRegistration();
        String countryWelsh = certificatePageObjectVTG5W.getCountryOfRegistration();
        String countryFailWelsh = certificatePageObjectVTG30W.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), countryFail);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), countryWelsh);
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), countryFailWelsh);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTG5.getMakeAndModel();
        String makeAndModelFail = certificatePageObjectVTG30.getMakeAndModel();
        String makeAndModelWelsh = certificatePageObjectVTG5W.getMakeAndModel();
        String makeAndModelFailWelsh = certificatePageObjectVTG30W.getMakeAndModel();

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                makeAndModelFail
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModelWelsh
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                makeAndModelFailWelsh
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObjectVTG5.getVehicleCategory();
        String vehicleCategoryFail = certificatePageObjectVTG30.getVehicleCategory();
        String vehicleCategoryWelsh = certificatePageObjectVTG5W.getVehicleCategory();
        String vehicleCategoryFailWelsh = certificatePageObjectVTG30W.getVehicleCategory();

        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory);
        assertEquals(testCertificate.getFailData().getVehicleEuClassification(), vehicleCategoryFail);
        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategoryWelsh);
        assertEquals(testCertificate.getFailData().getVehicleEuClassification(), vehicleCategoryFailWelsh);
    }

    @Test
    public void verifyMileage() {
        String mileage = certificatePageObjectVTG5.getMileage();
        String mileageFail = certificatePageObjectVTG30.getMileage();
        String mileageWelsh = certificatePageObjectVTG5W.getMileage();
        String mileageFailWelsh = certificatePageObjectVTG30W.getMileage();

        assertEquals(testCertificate.getData().getFormattedCurrentOdometer(), mileage);
        assertEquals(testCertificate.getFailData().getFormattedCurrentOdometer(), mileageFail);
        assertEquals("20,000 cilometrau", mileageWelsh);
        assertEquals("20,000 cilometrau", mileageFailWelsh);
    }

    @Test
    public void verifyPrsDefects() {
        List<String> prsDefects = certificatePageObjectVTG5.getDefectSummaryComponent().getPrsDefects().eachText();
    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> advisoryDefects = certificatePageObjectVTG5.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyAdvisoryDefectsWelsh() {
        List<String> advisoryDefects = certificatePageObjectVTG5W.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTG5.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTG5W.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObjectVTG5.getDateOfTheTest();
        String dateOfTheTestFail = certificatePageObjectVTG30.getDateOfTheTest();
        String dateOfTheTestWelsh = certificatePageObjectVTG5W.getDateOfTheTest();
        String dateOfTheTestFailWelsh = certificatePageObjectVTG30W.getDateOfTheTest();

        assertEquals(testCertificate.getData().getDateOfTheTest(), dateOfTheTest);
        assertEquals(testCertificate.getFailData().getDateOfTheTest(), dateOfTheTestFail);
        assertEquals(testCertificate.getData().getDateOfTheTest(), dateOfTheTestWelsh);
        assertEquals(testCertificate.getFailData().getDateOfTheTest(), dateOfTheTestFailWelsh);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String locationOfTheTest = certificatePageObjectVTG5.getLocationOfTheTest();
        String locationOfTheTestFail = certificatePageObjectVTG30.getLocationOfTheTest();
        String locationOfTheTestWelsh = certificatePageObjectVTG5W.getLocationOfTheTest();
        String locationOfTheTestFailWelsh = certificatePageObjectVTG30W.getLocationOfTheTest();

        String pNumber = testCertificate.getData().getTestStationPNumber();
        String testStationName = testCertificate.getData().getTestStationName();

        String pNumberFail = testCertificate.getFailData().getTestStationPNumber();
        String testStationNameFail = testCertificate.getFailData().getTestStationName();

        assertEquals(pNumber + ", " + testStationName, locationOfTheTest);
        assertEquals(pNumberFail + ", " + testStationNameFail, locationOfTheTestFail);
        assertEquals(pNumber + ", " + testStationName, locationOfTheTestWelsh);
        assertEquals(pNumberFail + ", " + testStationNameFail, locationOfTheTestFailWelsh);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorName() {
        String orgName = certificatePageObjectVTG5.getTestingOrganisationAndInspectorsName();
        String orgNameFail = certificatePageObjectVTG30.getTestingOrganisationAndInspectorsName();
        String orgNameWelsh = certificatePageObjectVTG5W.getTestingOrganisationAndInspectorsName();
        String orgNameFailWelsh = certificatePageObjectVTG30W.getTestingOrganisationAndInspectorsName();

        String organisation = testCertificate.getData().getTestingOrganisation();
        String organisationFail = testCertificate.getFailData().getTestingOrganisation();
        String organisationWelsh = testCertificate.getFailData().getTestingOrganisationWelsh();

        String testerName = testCertificate.getData().getIssuersName();
        String testerNameFail = testCertificate.getFailData().getIssuersName();

        assertEquals(organisation + " " + testerName, orgName);
        assertEquals(organisationFail + " " + testerNameFail, orgNameFail);
        assertEquals(organisationWelsh + " " + testerName, orgNameWelsh);
        assertEquals(organisationWelsh + " " + testerNameFail, orgNameFailWelsh);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTG5.getTestNumber();
        String testNumberFail = certificatePageObjectVTG30.getTestNumber();
        String testNumberWelsh = certificatePageObjectVTG5W.getTestNumber();
        String testNumberFailWelsh = certificatePageObjectVTG30W.getTestNumber();

        assertEquals(testCertificate.getData().getTestNumber(), testNumber);
        assertEquals(testCertificate.getFailData().getTestNumber(), testNumberFail);
        assertEquals(testCertificate.getData().getTestNumber(), testNumberWelsh);
        assertEquals(testCertificate.getFailData().getTestNumber(), testNumberFailWelsh);
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
