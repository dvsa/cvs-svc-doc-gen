package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.CvsHgvTrlFailBilingual;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateData.FAILED_SUMMARY_HEADER;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateDataWelsh.FAILED_SUMMARY_HEADER_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

public class CvsHgvTrlFailBilingualTest {

    protected HtmlGenerator htmlGenerator;
    protected CvsHgvTrlFailBilingual testCertificate;
    protected CertificatePageObject certificatePageObjectVTG30;
    protected CertificatePageObject certificatePageObjectVTG30W;

    public CvsHgvTrlFailBilingualTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getCvsHgvTrlFailBilingual();

        String certHtmlVTG30 = htmlGenerator.generate(testCertificate).get(0);
        String certHtmlVTG30W = htmlGenerator.generate(testCertificate).get(1);

        certificatePageObjectVTG30 = new CertificatePageObject(certHtmlVTG30);
        certificatePageObjectVTG30W = new CertificatePageObject(certHtmlVTG30W);
    }

    @Test
    public void verifyResultSummaries() {
        String resultName = certificatePageObjectVTG30.getDefectSummaryComponent().getResultNameItem().text();
        String resultNameWelsh = certificatePageObjectVTG30W.getDefectSummaryComponent().getResultNameItem().text();

        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER),
                resultName
        );
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", FAILED_SUMMARY_HEADER_WELSH),
                resultNameWelsh
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObjectVTG30.getVin();
        String vin2 = certificatePageObjectVTG30W.getVin();

        assertEquals(testCertificate.getFailData().getRawVin(), vin);
        assertEquals(testCertificate.getFailData().getRawVin(), vin2);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObjectVTG30.getCountryOfRegistration();
        String country2 = certificatePageObjectVTG30W.getCountryOfRegistration();

        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), country);
        assertEquals(testCertificate.getFailData().getCountryOfRegistrationCode(), country2);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObjectVTG30.getMakeAndModel();
        String makeAndModel2 = certificatePageObjectVTG30W.getMakeAndModel();

        assertEquals(testCertificate.getFailData().getMake() + ", " + testCertificate.getFailData().getModel(), makeAndModel);
        assertEquals(testCertificate.getFailData().getMake() + ", " + testCertificate.getFailData().getModel(), makeAndModel2);
    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> advisoryDefects = certificatePageObjectVTG30.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObjectVTG30.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDangerousDefects() {
        List<String> dangerousDefects = certificatePageObjectVTG30.getDefectSummaryComponent().getDangerousDefects().eachText();
        assertEquals(1, dangerousDefects.size());
    }

    @Test
    public void verifyMajorDefects() {
        List<String> majorDefects = certificatePageObjectVTG30.getDefectSummaryComponent().getMajorDefects().eachText();
        assertEquals(1, majorDefects.size());
    }

    @Test
    public void verifyAdvisoryDefectsWelsh() {
        List<String> advisoryDefects = certificatePageObjectVTG30W.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefectsWelsh() {
        List<String> minorDefects = certificatePageObjectVTG30W.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDangerousDefectsWelsh() {
        List<String> dangerousDefects = certificatePageObjectVTG30W.getDefectSummaryComponent().getDangerousDefectsWelshCVS().eachText();
        assertEquals(1, dangerousDefects.size());
    }

    @Test
    public void verifyMajorDefectsWelsh() {
        List<String> majorDefects = certificatePageObjectVTG30W.getDefectSummaryComponent().getMajorDefectsWelshCVS().eachText();
        assertEquals(1, majorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObjectVTG30.getDateOfTheTest();
        String dateOfTheTest2 = certificatePageObjectVTG30W.getDateOfTheTest();

        assertEquals(testCertificate.getFailData().getDateOfTheTest(), dateOfTheTest);
        assertEquals(testCertificate.getFailData().getDateOfTheTest(), dateOfTheTest2);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObjectVTG30.getLocationOfTheTest();
        String location2 = certificatePageObjectVTG30W.getLocationOfTheTest();

        String pNumber = testCertificate.getFailData().getTestStationPNumber();
        String testStationName = testCertificate.getFailData().getTestStationName();

        assertEquals(pNumber + ", " + testStationName, location);
        assertEquals(pNumber + ", " + testStationName, location2);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String orgName = certificatePageObjectVTG30.getTestingOrganisationAndInspectorsName();
        String orgNameWelsh = certificatePageObjectVTG30W.getTestingOrganisationAndInspectorsName();

        String organisation = testCertificate.getFailData().getTestingOrganisation();
        String testerName = testCertificate.getFailData().getIssuersName();

        String organisationWelsh = testCertificate.getFailData().getTestingOrganisationWelsh();
        String testerNameWelsh = testCertificate.getFailData().getIssuersName();

        assertEquals(organisation + " " + testerName, orgName);
        assertEquals(organisationWelsh + " " + testerNameWelsh, orgNameWelsh);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObjectVTG30.getTestNumber();
        String testNumber2 = certificatePageObjectVTG30W.getTestNumber();

        assertEquals(testCertificate.getFailData().getTestNumber(), testNumber);
        assertEquals(testCertificate.getFailData().getTestNumber(), testNumber2);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObjectVTG30W.getRecallsHeader();
        String contentText = certificatePageObjectVTG30W.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObjectVTG30.getRecallsHeader();
        String contentText = certificatePageObjectVTG30.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
