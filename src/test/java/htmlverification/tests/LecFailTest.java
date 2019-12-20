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

public class LecFailTest {

    protected HtmlGenerator htmlGenerator;
    protected LecCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    public LecFailTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        testCertificate = CertificateTestDataProvider.getLecFail();
        List<String> certHtml = htmlGenerator.generate(testCertificate);
        certificatePageObject = new CertificatePageObject(certHtml.get(0));
    }

    @Test
    public void verifySerialNumber() {
        String serial = certificatePageObject.getSerialNumber();
        assertEquals(testCertificate.getLecData().getSerialNumber(),serial);
    }

    @Test
    public void verifyAdditionalNotes() {
        String notes = certificatePageObject.getAdditionalNotes();
        assertEquals(testCertificate.getLecData().getAdditionalNotes(), notes);
    }
}
