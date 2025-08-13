package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.VTG30W;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class VTG30WTest {

    protected HtmlGenerator htmlGenerator;
    protected VTG30W vtg30w;
    protected CertificatePageObject certificatePageObject;

    public VTG30WTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        vtg30w = CvsCertificateTestDataProvider.getVTG30W();
        String certHtml = htmlGenerator.generate(vtg30w).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
    }

    @Test
    public void testMakeModelWhereModelIsNotSet() {
        vtg30w.getFailData().setModel("");
        String certHtml = htmlGenerator.generate(vtg30w).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#make-model").text();
        assertEquals("MERCEDES,", actual);
    }

    @Test
    public void testMakeModelWhereMakeIsNotSet() {
        vtg30w.getFailData().setMake("");
        String certHtml = htmlGenerator.generate(vtg30w).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);

        String actual = certificatePageObject.getElement("#make-model").text();
        assertEquals(", Z WAGON", actual);
    }

    @Test
    public void verifyTrn() {
        String actual = certificatePageObject.getElement("#trn").text();
        assertEquals("ABC1234", actual);
    }

    @Test
    public void verifyEmptyTrn() {
        vtg30w.getFailData().setTrn("");
        String certHtml = htmlGenerator.generate(vtg30w).get(0);
        certificatePageObject = new CertificatePageObject(certHtml);
        String actual = certificatePageObject.getElement("#trn").text();
        assertEquals("", actual);
    }

    @Test
    public void verifyRecallsWelsh() {
        String titleText = certificatePageObject.getRecallsHeader();
        String contentText = certificatePageObject.getRecallsBody();
        assertEquals("Mae gan y cerbyd hwn wedi cael ei alw'n ôl", titleText);
        assertEquals("Cysylltwch â'ch agosaf Aston Martin deliwr i gael gwybodaeth ac i drefnu atgyweiriad am ddim.", contentText);
    }
}
