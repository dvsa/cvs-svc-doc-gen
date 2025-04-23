package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTG5W;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.TESTING_ORGANISATION_WELSH;
import static uk.gov.dvsa.model.mot.results.Summary.EU_NUMBER_SUMMARY_HEADER;
import static uk.gov.dvsa.view.cvs.CvsOdometerReadingFormatter.MILES_WELSH;

public class VTG5WTest {

    protected HtmlGenerator htmlGenerator;
    protected VTG5W vtg5W;
    protected CertificatePageObject certificatePageObject;

    public VTG5WTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg5W = CvsCertificateTestDataProvider.getVTG5W();
        String certHtml = htmlGenerator.generate(vtg5W).get(0);
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
        assertEquals(vtg5W.getData().getRawVin(), vin);
    }

    @Test
    public void verifyRegistrationNumber() {
        String vrm = certificatePageObject.getRegistrationNumber();
        assertEquals(vtg5W.getData().getRawVrm(), vrm);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String countryOfRegistration = certificatePageObject.getCountryOfRegistration();
        assertEquals(vtg5W.getData().getCountryOfRegistrationCode(), countryOfRegistration);
    }

    @Test
    public void verifyVehicleEuClassification() {
        String vehicleEuClassification = certificatePageObject.getVehicleCategory();
        assertEquals(vtg5W.getData().getVehicleEuClassification(), vehicleEuClassification);
    }

    @Test
    public void verifyMileage() {
        String value = certificatePageObject.getMileage();
        assertEquals("20,000 " + MILES_WELSH, value);
    }

    @Test
    public void verifyOdomoterHistory() {
        String actual = certificatePageObject.getElement("#mileage-history").text();
        String expected = "12,000 milltiroedd 13.08.2022 7,000 milltiroedd 02.08.2021";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyOdometerHistoryCount() {
        List<Object> list = List.of(certificatePageObject.getMileageHistoryComponent().getEntries().toArray());
        assertEquals(2, list.size());
    }

    @Test
    public void verifyAdvisories() {
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
        assertEquals(vtg5W.getData().getDateOfTheTest(), dateOfTheTest);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals(vtg5W.getData().getExpiryDate(), expiryDate);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String locationOfTheTest = certificatePageObject.getLocationOfTheTest();
        assertEquals(vtg5W.getData().getLocationOfTheTest(), locationOfTheTest);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String orgAndName = certificatePageObject.getTestingOrganisationAndInspectorsName();
        assertEquals(
                String.format("%s %s",
                        TESTING_ORGANISATION_WELSH,
                        vtg5W.getData().getIssuersName()),
                orgAndName
        );
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObject.getTestNumber();
        assertEquals(vtg5W.getData().getTestNumber(), testNumber);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }
}
