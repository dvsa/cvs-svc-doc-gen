package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import org.junit.Before;
import org.junit.Test;

import htmlverification.service.CvsCertificateTestDataProvider;
import uk.gov.dvsa.model.cvs.VTG5A;
import uk.gov.dvsa.service.HtmlGenerator;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class Vtg5aTest {

    protected HtmlGenerator htmlGenerator;
    protected VTG5A vtg5a;
    protected CertificatePageObject certificatePageObject;

    public Vtg5aTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg5a = CvsCertificateTestDataProvider.getVtg5a();
        String certHtml = htmlGenerator.generate(vtg5a).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void testMakeModelWhereModelIsNotSet() {

        vtg5a.getData().setModel("");
        String certHtml = htmlGenerator.generate(vtg5a).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#make-model").text();
        assertEquals("Aston Martin,", actual);
    }

    @Test
    public void testMakeModelWhereMakeIsNotSet() {

        vtg5a.getData().setMake("");
        String certHtml = htmlGenerator.generate(vtg5a).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#make-model").text();
        assertEquals(", DB11", actual);
    }

    @Test
    public void verifyTrn() {
        String actual = certificatePageObject.getElement("#trn").text();
        assertEquals("ABC1234", actual);
    }

    @Test
    public void verifyEmptyTrn() {
        vtg5a.getData().setTrn("");
        String certHtml = htmlGenerator.generate(vtg5a).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
        String actual = certificatePageObject.getElement("#trn").text();
        assertEquals("", actual);
    }

}
