package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.LecCertificate;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LecPassTest {

    protected HtmlGenerator htmlGenerator;
    protected LecCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public LecPassTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CertificateTestDataProvider.getLecPass();
        List<String> certHtml = htmlGenerator.generate(testCertificate);
        certificatePageObject = new CertificatePageObject(certHtml.get(0));
    }

    @Test
    public void verifySerialNumber() {
        String serial = certificatePageObject.getSerialNumber();
        assertEquals(testCertificate.getLecData().getSerialNumber(),serial);
    }

    @Test
    public void verifyExpiryDate() {
        String expiryDate = certificatePageObject.getExpiryDate();
        assertEquals(testCertificate.getLecData().getExpiryDate(),expiryDate);
    }

    @Test
    public void verifyVrm() {
        String vrm = certificatePageObject.getVrm();
        assertEquals(String.join(" ",testCertificate.getLecData().getVrm().split("")),vrm);
    }

    @Test
    public void verifySmokeTestLimit() {
        String smokeTestLimit = certificatePageObject.getSmokeTestLimit();
        assertEquals(testCertificate.getLecData().getSmokeTestLimit(), smokeTestLimit);
    }

    @Test
    public void verifyTickedModificationType() {
        assertEquals(testCertificate.getLecData().getModificationTypeP(), false);
        assertEquals(testCertificate.getLecData().getModificationTypeM(), false);
        assertEquals(testCertificate.getLecData().getModificationTypeG(), true);
        String tickedModType = certificatePageObject.getTickedModificationType();
        assertEquals(testCertificate.getLecData().getModificationType(), tickedModType);
    }

    @Test
    public void verifyMakeAndModel() {
        String makeAndModel = certificatePageObject.getMakeAndModel();
        assertEquals(testCertificate.getLecData().getMake()+" / "+testCertificate.getLecData().getModel(), makeAndModel);
    }

    @Test
    public void verifyTrapOrMod() {
        LecCertificate testCert = CertificateTestDataProvider.getLecPass();
        assertEquals(testCert.getLecData().getModificationType(), "G");
        assertEquals(testCert.getLecData().getParticulateTrapFittedOrModificationTypeUsed(), testCert.getLecData().getModificationTypeUsed());

        CertificatePageObject certPageObject = new CertificatePageObject(htmlGenerator.generate(testCert).get(0));
        String trapOrMod = certPageObject.getTrapOrMod();
        assertEquals(testCert.getLecData().getModificationTypeUsed(), trapOrMod);

        testCert.getLecData().setModificationType("P");
        certPageObject = new CertificatePageObject(htmlGenerator.generate(testCert).get(0));
        trapOrMod = certPageObject.getTrapOrMod();
        assertEquals(testCert.getLecData().getParticulateTrapFitted(), trapOrMod);
    }
}
