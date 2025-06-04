package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTP20W;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;
import uk.gov.dvsa.service.HtmlGenerator;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

import java.io.IOException;
import java.util.List;

public class VTP20WTest {

    protected HtmlGenerator htmlGenerator;
    protected VTP20W vtp20W;
    protected CertificatePageObject certificatePageObject;

    public VTP20WTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtp20W = CvsCertificateTestDataProvider.getVtp20W();
        String certHtml = htmlGenerator.generate(vtp20W).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
                String.format("(%s) %s", EU_NUMBER_SUMMARY_HEADER, CvsMotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH),
                resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(vtp20W.getData().getRawVin(), vin);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObject.getCountryOfRegistration();
        assertEquals(vtp20W.getData().getCountryOfRegistrationCode(), country);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        assertEquals(
                String.format("%s, %s", vtp20W.getData().getMake(), vtp20W.getData().getModel()),
                makeAndModel
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObject.getVehicleCategory();
        assertEquals(vtp20W.getData().getVehicleEuClassification(), vehicleCategory);
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
        assertEquals("12.11.2018", dateOfTheTest);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals("12.10.2018", expiryDate);
    }

    @Test
    public void verifyLocationOfTheTest() {
        String location = certificatePageObject.getLocationOfTheTest();
        assertEquals("P12345, POPULAR GARAGES", location);
    }

    @Test
    public void verifyTestingOrganisationAndInspectorsName() {
        String text = certificatePageObject.getTestingOrganisationAndInspectorsName();
        assertEquals("ASIANTAETH SAFONAU GYRWYR A CHERBYDAU R.DREWNO", text);
    }

    @Test
    public void verifyMotTestNumber() {
        String testNumber = certificatePageObject.getTestNumber();
        assertEquals("1806 8140 0628", testNumber);
    }

    @Test
    public void verifySignature() {
        String signatureElement = certificatePageObject.getSignatureImageSrc();
        String expected = vtp20W.getSignature().getFormattedImageData();

        assertEquals(expected, signatureElement);
    }

    @Test
    public void verifyTitle() {
        String titleText = certificatePageObject.getElement(".header__title").text();
        String expected = "Tystysgrif prawf MOT (" + vtp20W.getTestType() + ")";
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyCurrentOdomoter() {
        String actual = certificatePageObject.getElement("#mileage").text();
        String expected = "22,341 milltiroedd";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyOdomoterHistory() {
        String actual = certificatePageObject.getElement("#mileage-history").text();
        String expected = "120 milltiroedd 01.02.2016 330 milltiroedd 30.01.2017";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyOdometerHistoryCount() {
        List<Object> list = List.of(certificatePageObject.getMileageHistoryComponent().getEntries().toArray());
        assertEquals(2, list.size());
    }

    @Test
    public void verifyOdometerHistoryMessageWhenNoHistoryExists() {
        vtp20W = CvsCertificateTestDataProvider.getVtp20wWithNoOdometerHistory();
        String certHtml = htmlGenerator.generate(vtp20W).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#no_data_message").text();
        String expected = "Dim data ar gael";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }
}
