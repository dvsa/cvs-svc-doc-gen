package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.mot.MotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import static htmlverification.framework.component.DefectSummaryComponent.ADVISORIES_HEADER_TEXT;
import static htmlverification.framework.component.DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH;
import static htmlverification.framework.component.DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT;
import static htmlverification.framework.component.DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.dvsa.model.mot.certificateData.MotCertificateData.PASS_WITH_DEFECTS_HEADER;
import static uk.gov.dvsa.model.mot.certificateData.MotCertificateDataWelsh.PASS_WITH_DEFECTS_HEADER_WELSH;

public class VT20WTests {
    private static final int FIRST_WELSH_PAGE_NUMBER = 2;
    private static final int FIRST_ENGLISH_PAGE_NUMBER = 1;

    private HtmlGenerator htmlGenerator;
    private PDFGenerationService pdfGenerationService;
    private MotCertificate testCertificate;
    private PDFParser pdfParser;
    private PdfReader pdfReader;

    public VT20WTests() {
        this.testCertificate = CertificateTestDataProvider.getVt20W();
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
    public void verifyTitle() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains("Tystysgrif prawf MOT"));
    }

    @Test
    public void verifyHeader() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(PASS_WITH_DEFECTS_HEADER_WELSH));
    }

    @Test
    public void verifyRfrs() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(MINOR_DEFECTS_HEADER_TEXT_WELSH));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_WELSH_PAGE_NUMBER).contains(ADVISORIES_HEADER_TEXT_WELSH));
    }

    @Test
    public void verifyVinOnThirdPage() throws Exception {
        String vinText = "VIN: " + CertificateTestDataProvider.VIN;

        boolean isVinOnFirstPage = pdfParser.getRawText(pdfReader, 1).contains(vinText);
        boolean isVinOnSecondPage = pdfParser.getRawText(pdfReader, 2).contains(vinText);
        boolean isVinOnThirdPage = pdfParser.getRawText(pdfReader, 3).contains(vinText);

        assertFalse(isVinOnFirstPage);
        assertFalse(isVinOnSecondPage);
        assertTrue(isVinOnThirdPage);
    }

    @Test
    public void verifyAdditionalInformationIsNotDisplayed() throws Exception {
        String additionalInfoText = testCertificate.getData().getAdditionalInformation();

        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            assertFalse(pdfParser.getRawText(pdfReader, i).contains(additionalInfoText));
        }
    }

    @Test
    public void verifyEnglishCertificateTitleIsOnThirdPage() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_ENGLISH_PAGE_NUMBER).contains("MOT test certificate"));
    }

    @Test
    public void verifyEnglishHeaderIsOnThirdPage() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_ENGLISH_PAGE_NUMBER).contains(PASS_WITH_DEFECTS_HEADER));
    }

    @Test
    public void verifyEnglishRfrs() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_ENGLISH_PAGE_NUMBER).contains(MINOR_DEFECTS_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, FIRST_ENGLISH_PAGE_NUMBER).contains(ADVISORIES_HEADER_TEXT));
    }
}
