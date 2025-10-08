package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTG5;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

public class VTG5Test {

    protected HtmlGenerator htmlGenerator;
    protected VTG5 vtg5;
    protected CertificatePageObject certificatePageObject;

    public VTG5Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg5 = CvsCertificateTestDataProvider.getVtg5();
        String certHtml = htmlGenerator.generate(vtg5).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultsSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
                String.format("%s%s%s %s", "(", EU_NUMBER_SUMMARY_HEADER, ")", CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER),
                resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(vtg5.getData().getRawVin(), vin);
    }

    @Test
    public void verifyRegistrationNumber() {
        String vrm = certificatePageObject.getRegistrationNumber();
        assertEquals(vtg5.getData().getRawVrm(), vrm);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String countryOfRegistration = certificatePageObject.getCountryOfRegistration();
        assertEquals(vtg5.getData().getCountryOfRegistrationCode(), countryOfRegistration);
    }

    @Test
    public void verifyVehicleEuClassification() {
        String vehicleEuClassification = certificatePageObject.getVehicleCategory();
        assertEquals(vtg5.getData().getVehicleEuClassification(), vehicleEuClassification);
    }

    @Test
    public void verifyMileage() {
        String value = certificatePageObject.getMileage();
        assertEquals(vtg5.getData().getFormattedCurrentOdometer(), value);
    }

    @Test
    public void verifyOdometerHistoryCount() {
        List<Object> list = List.of(certificatePageObject.getMileageHistoryComponent().getEntries().toArray());
        assertEquals(2, list.size());
    }

    @Test
    public void verifyAdvisories() {
        List<String> advisoryDefects = certificatePageObject.getDefectSummaryComponent().getAdvisories().eachText();
        assertEquals(vtg5.getData().getAdvisoryDefects().size(), advisoryDefects.size());
    }

    @Test
    public void verifyMinorDefect() {
        List<String> minorDefects = certificatePageObject.getDefectSummaryComponent().getMinorDefects().eachText();
        assertEquals(vtg5.getData().getMinorDefects().size(), minorDefects.size());
    }

    @Test
    public void verifyDateOfTheTest() {
        String dateOfTheTest = certificatePageObject.getDateOfTheTest();
        assertEquals(vtg5.getData().getDateOfTheTest(), dateOfTheTest);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals(vtg5.getData().getExpiryDate(), expiryDate);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String locationOfTheTest = certificatePageObject.getLocationOfTheTest();
        assertEquals(vtg5.getData().getLocationOfTheTest(), locationOfTheTest);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String orgAndName = certificatePageObject.getTestingOrganisationAndInspectorsName();
        assertEquals(
                String.format("%s %s",
                        vtg5.getData().getTestingOrganisation(),
                        vtg5.getData().getIssuersName()),
                orgAndName
        );
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObject.getTestNumber();
        assertEquals(vtg5.getData().getTestNumber(), testNumber);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }
}
