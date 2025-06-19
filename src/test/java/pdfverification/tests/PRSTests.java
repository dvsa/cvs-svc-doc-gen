package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.mot.PRS;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import static htmlverification.framework.component.DefectSummaryComponent.ADVISORIES_HEADER_TEXT;
import static htmlverification.framework.component.DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT;
import static htmlverification.framework.component.DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT;
import static htmlverification.framework.component.DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.dvsa.model.mot.certificateData.MotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.mot.certificateData.MotFailCertificateData.FAILED_SUMMARY_HEADER;

public class PRSTests {
    private static final int FIRST_PASS_PAGE_NUMBER = 1;
    private static final int FIRST_FAIL_PAGE_NUMBER = 3;

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private PRS testCertificate;
    private PDFParser pdfParser;
    private PdfReader pdfReader;

    public PRSTests() {
        this.testCertificate = CertificateTestDataProvider.getPRS();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void before() throws Exception {
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitles() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_PASS_PAGE_NUMBER).contains("MOT test certificate"));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_FAIL_PAGE_NUMBER).contains("Refusal of MOT test certificate"));
    }

    @Test
    public void verifyHeaders() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_PASS_PAGE_NUMBER).contains(PASS_WITH_DEFECTS_HEADER));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_FAIL_PAGE_NUMBER).contains(FAILED_SUMMARY_HEADER));
    }

    @Test
    public void verifyRfrs() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_PASS_PAGE_NUMBER).contains(MINOR_DEFECTS_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_PASS_PAGE_NUMBER).contains(ADVISORIES_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_FAIL_PAGE_NUMBER).contains(MAJOR_DEFECTS_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_FAIL_PAGE_NUMBER).contains(DANGEROUS_DEFECTS_HEADER_TEXT));
    }

    @Test
    public void verifyVinOnThirdPage() throws Exception {
        String vinText = "VIN: " + CertificateTestDataProvider.VIN;

        boolean isVinOnFirstPage = pdfParser.getRawText(pdfReader, 1).contains(vinText);
        boolean isVinOnSecondPage = pdfParser.getRawText(pdfReader, 2).contains(vinText);
        boolean isVinOnThirdPage = pdfParser.getRawText(pdfReader, 3).contains(vinText);
        boolean isVinOnForthPage = pdfParser.getRawText(pdfReader, 4).contains(vinText);

        assertFalse(isVinOnFirstPage);
        assertTrue(isVinOnSecondPage);
        assertFalse(isVinOnThirdPage);
        assertTrue(isVinOnForthPage);
    }
}
