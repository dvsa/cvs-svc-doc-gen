package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsTrlPRSBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateData.FAILED_SUMMARY_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateDataWelsh.FAILED_SUMMARY_HEADER_WELSH;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;

public class CvsTrlPrsBilingualTest {
    protected HtmlGenerator htmlGenerator;
    protected CvsTrlPRSBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTG5A;
    protected CertificatePageObject certificatePageObjectVTG5AW;
    protected CertificatePageObject certificatePageObjectVTG30;
    protected CertificatePageObject certificatePageObjectVTG30W;

    public CvsTrlPrsBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsTrlPrsBilingual();

        String certHtmlVTG5A = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTG5AW = htmlGenerator.generate(testCertificate).get(2);
        String certHtmlVTG30 = htmlGenerator.generate(testCertificate).get(1);
        String certHtmlVTG30W = htmlGenerator.generate(testCertificate).get(3);

        certificatePageObjectVTG5A = new CertificatePageObject(certHtmlVTG5A);
        certificatePageObjectVTG5AW = new CertificatePageObject(certHtmlVTG5AW);
        certificatePageObjectVTG30 = new CertificatePageObject(certHtmlVTG30);
        certificatePageObjectVTG30W = new CertificatePageObject(certHtmlVTG30W);
    }

    @Test
    public void verifyResultSummaries() {
        String resultNameVTG5A = certificatePageObjectVTG5A.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameVTG5AWelsh = certificatePageObjectVTG5AW.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameVTG30 = certificatePageObjectVTG30.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameVTG30Welsh = certificatePageObjectVTG30W.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER),
                resultNameVTG5A);
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", PASS_WITH_DEFECTS_HEADER_WELSH),
                resultNameVTG5AWelsh);
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER),
                resultNameVTG30
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER_WELSH),
                resultNameVTG30Welsh
        );
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTG5A.getCountryOfRegistration();
        String country2 = certificatePageObjectVTG5AW.getCountryOfRegistration();
        String country3 = certificatePageObjectVTG30.getCountryOfRegistration();
        String country4 = certificatePageObjectVTG30W.getCountryOfRegistration();

        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country2);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country3);
        assertEquals(testCertificate.getData().getCountryOfRegistrationCode(), country4);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTG5A.getMakeAndModel();
        String makeAndModel2 = certificatePageObjectVTG5AW.getMakeAndModel();
        String makeAndModel3 = certificatePageObjectVTG30.getMakeAndModel();
        String makeAndModel4 = certificatePageObjectVTG30W.getMakeAndModel();

        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getData().getMake(), ",", testCertificate.getData().getModel()),
                makeAndModel2
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                makeAndModel3
        );
        assertEquals(
                String.format("%s%s %s", testCertificate.getFailData().getMake(), ",", testCertificate.getFailData().getModel()),
                makeAndModel4
        );
    }


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
        String dateOfTheTest3 = certificatePageObjectVTG30.getDateOfTheTest();
        String dateOfTheTest4 = certificatePageObjectVTG30W.getDateOfTheTest();

        assertEquals("12.11.2018", dateOfTheTest);
        assertEquals("12.11.2018", dateOfTheTest2);
        assertEquals("12.11.2018", dateOfTheTest3);
        assertEquals("12.11.2018", dateOfTheTest4);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObjectVTG5A.getLocationOfTheTest();
        String location2 = certificatePageObjectVTG5AW.getLocationOfTheTest();
        String location3 = certificatePageObjectVTG30.getLocationOfTheTest();
        String location4 = certificatePageObjectVTG30W.getLocationOfTheTest();

        assertEquals("P12345, POPULAR GARAGES", location);
        assertEquals("P12345, POPULAR GARAGES", location2);
        assertEquals("P12345, TEST STATION NAME", location3);
        assertEquals("P12345, TEST STATION NAME", location4);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObjectVTG5A.getTestingOrganisationAndInspectorsName();
        String text2 = certificatePageObjectVTG5AW.getTestingOrganisationAndInspectorsName();
        String text3 = certificatePageObjectVTG30.getTestingOrganisationAndInspectorsName();
        String text4 = certificatePageObjectVTG30W.getTestingOrganisationAndInspectorsName();

        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY R.DREWNO", text);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", text2);
        assertEquals("DRIVER AND VEHICLE STANDARDS AGENCY TESTER NAME", text3);
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU TESTER NAME", text4);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTG5A.getTestNumber();
        String testNumber2 = certificatePageObjectVTG5AW.getTestNumber();
        String testNumber3 = certificatePageObjectVTG30.getTestNumber();
        String testNumber4 = certificatePageObjectVTG30W.getTestNumber();

        assertEquals("1806 8140 0628", testNumber);
        assertEquals("1806 8140 0628", testNumber2);
        assertEquals("X01X00001", testNumber3);
        assertEquals("X01X00001", testNumber4);
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


