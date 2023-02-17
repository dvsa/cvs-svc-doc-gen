package htmlverification.tests;

import com.github.jknack.handlebars.Handlebars;
import htmlverification.framework.page_object.CertificatePageObject;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.MinistryPlate;
import uk.gov.dvsa.model.cvs.TrailerIntoService;
import uk.gov.dvsa.model.cvs.certificateData.ApplicantDetails;
import uk.gov.dvsa.service.HtmlGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TrailerIntoServiceTest {

    protected HtmlGenerator htmlGenerator;
    protected TrailerIntoService model;
    protected CertificatePageObject dom;

    public TrailerIntoServiceTest() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
    }

    @Before
    public void setup() throws IOException {
        model = CertificateTestDataProvider.getTrailerIntoService(3);
        String certHtml = htmlGenerator.generate(model).get(0);
        dom = new CertificatePageObject(certHtml);
    }

    @Test
    public void verifyApplicantDetails() {
        ApplicantDetails applicant = model.getApplicantDetails();
        assertEquals(applicant.getName(), dom.getElement("#applicant-details .name").text());
        assertEquals(applicant.getStreet(), dom.getElement("#applicant-details .street").text());
        assertEquals(applicant.getTown(), dom.getElement("#applicant-details .town").text());
        assertEquals(applicant.getCity(), dom.getElement("#applicant-details .city").text());
        assertEquals(applicant.getPostcode(), dom.getElement("#applicant-details .postcode").text());
    }

    @Test
    public void verifyDateRequested() {
        assertEquals("17 February 2023", dom.getElementById("letter-date-requested").text());
    }

    @Test
    public void verifyVin() {
        assertEquals(model.getVin(), dom.getElementById("vin").text());
    }

    @Test
    public void verifyTypeApprovalNumber() {
        assertEquals(model.getTypeApprovalNumber(), dom.getElementById("type-approval-number").text());
    }

    @Test
    public void verifyTrailerId() {
        assertEquals(model.getTrailerId(), dom.getElementById("trailer-id").text());
    }

    @Test
    public void verifyParagraph3() {
        assertEquals("On behalf of the UK approval authority and pursuant to the Road Vehicles (Approval) Regulations 2020, Chapter 2, Regulation 23  we hereby give consent to the supply of the trailer having the above identification numbers for use on a road in Great Britain or Northern Ireland.\n\n"
                + "Please ensure that the allocated identification number is permanently marked in a conspicuous position on the nearside, on the chassis or a fixed component.\n\n"
                + "To check the authenticity of this document, please contact the above number quoting the trailer ID number."
                , dom.getElementById("paragraph-3").text());
    }
}
