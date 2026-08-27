package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.AdrIIRefusalCertificate;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdrIIRefusalTest {

    protected HtmlGenerator htmlGenerator;
    protected AdrIIRefusalCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public AdrIIRefusalTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CvsCertificateTestDataProvider.getAdrRefusal();
        String certHtml = htmlGenerator.generate(testCertificate).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyVrm() {
        assertEquals(testCertificate.getAdrData().getVrm(), certificatePageObject.getTextByElementId("vrm"));
    }

    @Test
    public void verifyVin() {
        assertEquals(testCertificate.getAdrData().getVin(), certificatePageObject.getTextByElementId("vin"));
    }

    @Test
    public void verifyTestNumber() {
        assertEquals(testCertificate.getAdrData().getTestNumber(), certificatePageObject.getTextByElementId("testNumber"));
    }

    @Test
    public void verifyNotes() {
        assertTrue(certificatePageObject.getTextByElementId("notes")
                .contains("Lorem ipsum dolor sit amet"));
    }

    @Test
    public void verifyRoadworthinessReasonShownWhenSelected() {
        assertTrue(certificatePageObject.getTextByElementId("reason-roadworthiness")
                .contains("does not meet the required roadworthiness standard"));
    }

    @Test
    public void verifySpecialRequirementsReasonShownWhenSelected() {
        assertTrue(certificatePageObject.getTextByElementId("reason-special-requirements")
                .contains("special requirements of Part 9"));
    }

    @Test
    public void verifySignature() {
        assertEquals(testCertificate.getSignature().getFormattedImageData(), certificatePageObject.getSignature());
    }

    @Test
    public void verifyDate() {
        assertEquals(testCertificate.getAdrData().getDate(), certificatePageObject.getTextByElementId("date"));
    }
}
