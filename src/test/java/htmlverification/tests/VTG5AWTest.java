package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTG5AW;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.TESTING_ORGANISATION_WELSH;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

public class VTG5AWTest {

    protected HtmlGenerator htmlGenerator;
    protected VTG5AW vtg5AW;
    protected CertificatePageObject certificatePageObject;

    public VTG5AWTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg5AW = CvsCertificateTestDataProvider.getVTG5AW();
        String certHtml = htmlGenerator.generate(vtg5AW).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultsSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH),
                resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(vtg5AW.getData().getRawVin(), vin);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String countryOfRegistration = certificatePageObject.getCountryOfRegistration();
        assertEquals(vtg5AW.getData().getCountryOfRegistrationCode(), countryOfRegistration);
    }

    @Test
    public void verifyAdvisoryDefects() {
        List<String> advisoryDefects = certificatePageObject.getDefectSummaryComponent().getAdvisoriesWelshCVS().eachText();
        assertEquals(1, advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefects() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getMinorDefectsWelshCVS().eachText();
        assertEquals(1, minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObject.getDateOfTheTest();
        assertEquals(vtg5AW.getData().getDateOfTheTest(), dateOfTheTest);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals(vtg5AW.getData().getExpiryDate(), expiryDate);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String locationOfTheTest = certificatePageObject.getLocationOfTheTest();
        assertEquals(vtg5AW.getData().getLocationOfTheTest(), locationOfTheTest);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String orgAndName = certificatePageObject.getTestingOrganisationAndInspectorsName();
        assertEquals(
                String.format("%s %s",
                        TESTING_ORGANISATION_WELSH,
                        vtg5AW.getData().getIssuersName()),
                orgAndName
        );
    }
    @Test
    public void verifyTestNumber() {
        String testNumber = certificatePageObject.getTestNumber();
        assertEquals(vtg5AW.getData().getTestNumber(), testNumber);
    }
    @Test
    public void verifyEarliestDateOfTheNextTest() {
        String earliestDateOfNextTest = certificatePageObject.getEarliestDateOfTheNextTest();
        assertEquals(vtg5AW.getData().getEarliestDateOfTheNextTest(), earliestDateOfNextTest);
    }

    @Test
    public void verifyTrailerNumber() {
        String trlNo = certificatePageObject.getTrn();
        assertEquals(vtg5AW.getData().getTrn(), trlNo);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }
}
