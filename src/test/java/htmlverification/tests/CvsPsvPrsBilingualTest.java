package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsPsvPRSBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

public class CvsPsvPrsBilingualTest {
    protected HtmlGenerator htmlGenerator;
    protected CvsPsvPRSBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTP20;
    protected CertificatePageObject certificatePageObjectVTP30;
    protected CertificatePageObject certificatePageObjectVTP20Welsh;
    protected CertificatePageObject certificatePageObjectVTP30Welsh;

    public CvsPsvPrsBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsPsvPRSBilingual();
        String certHtmlVTP20 = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTP30 = htmlGenerator.generate(testCertificate).get(1);
        String certHtmlVTP20Welsh = htmlGenerator.generate(testCertificate).get(2);
        String certHtmlVTP30Welsh = htmlGenerator.generate(testCertificate).get(3);

        certificatePageObjectVTP20 = new CertificatePageObject(certHtmlVTP20);
        certificatePageObjectVTP30 = new CertificatePageObject(certHtmlVTP30);
        certificatePageObjectVTP20Welsh = new CertificatePageObject(certHtmlVTP20Welsh);
        certificatePageObjectVTP30Welsh = new CertificatePageObject(certHtmlVTP30Welsh);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTP20.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTP20Welsh.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER),
                resultName);
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER_WELSH),
                resultNameWelsh);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String vtp20CountryOfRegistration = certificatePageObjectVTP20.getCountryOfRegistration();
        String vtp30CountryOfRegistration = certificatePageObjectVTP30.getCountryOfRegistration();
        String vtp20WelshCountryOfRegistration = certificatePageObjectVTP20Welsh.getCountryOfRegistration();
        String vtp30WelshCountryOfRegistration = certificatePageObjectVTP30Welsh.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), vtp20CountryOfRegistration);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), vtp30CountryOfRegistration);
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), vtp20WelshCountryOfRegistration);
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), vtp30WelshCountryOfRegistration);
    }

    @Test
    public void verifyMakeAndModel() {
        String vtp20MakeAndModel = certificatePageObjectVTP20.getMakeAndModel();
        String vtp30MakeAndModel = certificatePageObjectVTP30.getMakeAndModel();
        String vtp20WelshMakeAndModel = certificatePageObjectVTP20Welsh.getMakeAndModel();
        String vtp30WelshMakeAndModel = certificatePageObjectVTP30Welsh.getMakeAndModel();

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                vtp20MakeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                vtp30MakeAndModel
        );

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                vtp20WelshMakeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                vtp30WelshMakeAndModel
        );
    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> vtp20AdvisoryDefects = certificatePageObjectVTP20.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(1, vtp20AdvisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTP20.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(1, minorDefects.size());
    }


    @Test
    public void verifyMajorDefects() {
        List<String> vtp30MajorDefects = certificatePageObjectVTP30.getDefectSummaryComponent().getMajorDefects().eachText();
        assertEquals(1, vtp30MajorDefects.size());
    }

    @Test
    public void verifyDangerousDefects() {
        List<String> vtp30DangerousDefects = certificatePageObjectVTP30.getDefectSummaryComponent().getDangerousDefects().eachText();
        assertEquals(1, vtp30DangerousDefects.size());
    }

    @Test
    public void verifyAdvisoryDefectsWelsh() {
        List<String> advisoryDefects = certificatePageObjectVTP30Welsh.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTP30Welsh.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyMajorDefectsWelsh() {
        List<String> vtp30MajorDefects = certificatePageObjectVTP30Welsh.getDefectSummaryComponent().getMajorDefectsWelshCVS().eachText();
        assertEquals(1, vtp30MajorDefects.size());
    }

    @Test
    public void verifyDangerousDefectsWelsh() {
        List<String> vtp30DangerousDefects = certificatePageObjectVTP30Welsh.getDefectSummaryComponent().getDangerousDefectsWelshCVS().eachText();
        assertEquals(1, vtp30DangerousDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String vtp20DateOfTheTest = certificatePageObjectVTP20.getDateOfTheTest();
        String vtp30DateOfTheTest = certificatePageObjectVTP30.getDateOfTheTest();
        String vtp20WelshDateOfTheTest = certificatePageObjectVTP20Welsh.getDateOfTheTest();
        String vtp30WelshDateOfTheTest = certificatePageObjectVTP30Welsh.getDateOfTheTest();

        assertEquals("12.11.2018", vtp20DateOfTheTest);
        assertEquals("12.11.2018", vtp30DateOfTheTest);
        assertEquals("12.11.2018", vtp20WelshDateOfTheTest);
        assertEquals("12.11.2018", vtp30WelshDateOfTheTest);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String vtp20LocationOfTheTest = certificatePageObjectVTP20.getLocationOfTheTest();
        String vtp30LocationOfTheTest = certificatePageObjectVTP30.getLocationOfTheTest();
        String vtp20WelshLocationOfTheTest = certificatePageObjectVTP20Welsh.getLocationOfTheTest();
        String vtp30WelshLocationOfTheTest = certificatePageObjectVTP30Welsh.getLocationOfTheTest();

        assertEquals("P12345, POPULAR GARAGES", vtp20LocationOfTheTest);
        assertEquals("P12345, POPULAR GARAGES", vtp30LocationOfTheTest);
        assertEquals("P12345, POPULAR GARAGES", vtp20WelshLocationOfTheTest);
        assertEquals("P12345, POPULAR GARAGES", vtp30WelshLocationOfTheTest);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String vtp20TestingOrganisationAndInspectorsName = certificatePageObjectVTP20.getTestingOrganisationAndInspectorsName();
        String vtp30TestingOrganisationAndInspectorsName = certificatePageObjectVTP30.getTestingOrganisationAndInspectorsName();
        String vtp20WelshTestingOrganisationAndInspectorsName = certificatePageObjectVTP20Welsh.getTestingOrganisationAndInspectorsName();
        String vtp30WelshTestingOrganisationAndInspectorsName1 = certificatePageObjectVTP30Welsh.getTestingOrganisationAndInspectorsName();

        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", vtp20TestingOrganisationAndInspectorsName);
        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", vtp30TestingOrganisationAndInspectorsName);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", vtp20WelshTestingOrganisationAndInspectorsName);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", vtp30WelshTestingOrganisationAndInspectorsName1);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTP20.getTestNumber();
        String testNumber2 = certificatePageObjectVTP30.getTestNumber();

        assertEquals("1806 8140 0628", testNumber);
        assertEquals("1806 8140 0628", testNumber2);
    }

    @Test
    public void verifyExpiryDateOnPassIsPresent() {
        String expiryDate = certificatePageObjectVTP20.getExpiryDate();
        String expiryDateWelsh = certificatePageObjectVTP20Welsh.getExpiryDate();

        assertEquals("12.10.2018", expiryDate);
        assertEquals("12.10.2018", expiryDateWelsh);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObjectVTP20Welsh.getRecallsHeader();
        String contentText = certificatePageObjectVTP20Welsh.getRecallsBody();
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

