package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsTrlPassBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

import java.io.IOException;
import java.util.List;

public class CvsTrlPassBilingualTest {

    protected HtmlGenerator htmlGenerator;
    protected CvsTrlPassBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTG5A;
    protected CertificatePageObject certificatePageObjectVTG5AW;

    public CvsTrlPassBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsTrlPassBilingual();

        String certHtmlVTG5A = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTG5AW = htmlGenerator.generate(testCertificate).get(1);

        certificatePageObjectVTG5A = new CertificatePageObject(certHtmlVTG5A);
        certificatePageObjectVTG5AW = new CertificatePageObject(certHtmlVTG5AW);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTG5A.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTG5AW.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER),
                resultName);
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER_WELSH),
                resultNameWelsh);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTG5A.getCountryOfRegistration();
        String country2 = certificatePageObjectVTG5AW.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country2);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTG5A.getMakeAndModel();
        String makeAndModel2 = certificatePageObjectVTG5AW.getMakeAndModel();

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel2
        );
    }

//    @Test
//    public void verifyVehicleCategory() {
//        String vehicleCategory = certificatePageObjectVTG5A.getVehicleCategory();
//        String vehicleCategory2 = certificatePageObjectVTG5AW.getVehicleCategory();
//
//        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory);
//        assertEquals(testCertificate.getData().getVehicleEuClassification(), vehicleCategory2);
//    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> advisoryDefects = certificatePageObjectVTG5A.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTG5A.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyAdvisoryDefectsWelsh() {
        List<String> advisoryDefects = certificatePageObjectVTG5AW.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTG5AW.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObjectVTG5A.getDateOfTheTest();
        String dateOfTheTest2 = certificatePageObjectVTG5AW.getDateOfTheTest();

        assertEquals("12.11.2018", dateOfTheTest);
        assertEquals("12.11.2018", dateOfTheTest2);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObjectVTG5A.getLocationOfTheTest();
        String location2 = certificatePageObjectVTG5AW.getLocationOfTheTest();

        assertEquals("P12345, POPULAR GARAGES", location);
        assertEquals("P12345, POPULAR GARAGES", location2);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObjectVTG5A.getTestingOrganisationAndInspectorsName();
        String text2 = certificatePageObjectVTG5AW.getTestingOrganisationAndInspectorsName();

        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", text);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", text2);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTG5A.getTestNumber();
        String testNumber2 = certificatePageObjectVTG5AW.getTestNumber();

        assertEquals("1806 8140 0628", testNumber);
        assertEquals("1806 8140 0628", testNumber2);
    }

    @Test
    public void verifyExpiryDateOnPassIsPresent() {
        String expiryDate = certificatePageObjectVTG5A.getExpiryDate();
        String expiryDate2 = certificatePageObjectVTG5AW.getExpiryDate();

        assertEquals("12.10.2018", expiryDate);
        assertEquals("12.10.2018", expiryDate2);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObjectVTG5AW.getRecallsHeader();
        String contentText = certificatePageObjectVTG5AW.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObjectVTG5A.getRecallsHeader();
        String contentText = certificatePageObjectVTG5A.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
