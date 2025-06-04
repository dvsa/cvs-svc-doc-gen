package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import org.junit.Before;
import org.junit.Test;

import htmlverification.service.CvsCertificateTestDataProvider;
import uk.gov.dvsa.model.cvs.VTG30;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class VTG30Test {

    protected HtmlGenerator htmlGenerator;
    protected VTG30 vtg30;
    protected CertificatePageObject certificatePageObject;

    public VTG30Test() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg30 = CvsCertificateTestDataProvider.getVtg30();
        String certHtml = htmlGenerator.generate(vtg30).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void testMakeModelWhereModelIsNotSet() {

        vtg30.getFailData().setModel("");
        String certHtml = htmlGenerator.generate(vtg30).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#make-model").text();
        assertEquals("Aston Martin,", actual);
    }

    @Test
    public void testMakeModelWhereMakeIsNotSet() {

        vtg30.getFailData().setMake("");
        String certHtml = htmlGenerator.generate(vtg30).get(0);
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
        vtg30.getFailData().setTrn("");
        String certHtml = htmlGenerator.generate(vtg30).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
        String actual = certificatePageObject.getElement("#trn").text();
        assertEquals("", actual);
    }

    @Test
    public void verifyRecallsEnglish() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("This vehicle has an outstanding recall", titleText);
        assertEquals("Contact your nearest Aston Martin dealership for information and to arrange a free repair.", contentText);
    }

}
