package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTP30W;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.gov.dvsa.model.cvs.certificateData.Summary.EU_NUMBER_SUMMARY_HEADER;

public class VTP30WTest {
    protected HtmlGenerator htmlGenerator;
    protected VTP30W vtp30W;
    protected CertificatePageObject certificatePageObject;

    public VTP30WTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtp30W = CvsCertificateTestDataProvider.getVtp30w();
        String certHtml = htmlGenerator.generate(vtp30W).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyResultSummary() {
        String resultName = certificatePageObject.getDefectSummaryComponent().getResultNameItem().text();
        assertEquals(
                String.format("(%s) %s", EU_NUMBER_SUMMARY_HEADER, "Methu"),
                resultName
        );
    }

    @Test
    public void verifyVin() {
        String vin = certificatePageObject.getVin();
        assertEquals(vtp30W.getFailData().getRawVin(), vin);
    }

    @Test
    public void verifyCountryOfRegistration() {
        String country = certificatePageObject.getCountryOfRegistration();
        assertEquals(vtp30W.getFailData().getCountryOfRegistrationCode(), country);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        assertEquals(
                String.format("%s, %s", vtp30W.getFailData().getMake(), vtp30W.getFailData().getModel()),
                makeAndModel
        );
    }

    @Test
    public void verifyVehicleCategory() {
        String vehicleCategory = certificatePageObject.getVehicleCategory();
        assertEquals(vtp30W.getFailData().getVehicleEuClassification(), vehicleCategory);
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
        String expected = vtp30W.getSignature().getFormattedImageData();

        assertEquals(expected, signatureElement);
    }

    @Test
    public void verifyTitle() {
        String titleText = certificatePageObject.getElement(".header__title").text();
        String expected = "Gwrthod tystysgrif prawf MOT";
        assertEquals(expected, titleText);
    }

    @Test
    public void verifyCurrentOdomoter() {
        String actual = certificatePageObject.getElement("#mileage").text();
        String expected = "22,341 milltiroedd";
        assertEquals(expected, actual);
    }

    @Test
    public void verifyRecalls() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }
}
