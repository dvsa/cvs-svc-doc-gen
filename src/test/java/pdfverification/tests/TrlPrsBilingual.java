package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.CvsMotCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;
import java.io.IOException;

import static htmlverification.framework.component.DefectSummaryComponent.*;
import static org.junit.Assert.assertTrue;
import static uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh.MINOR_DEFECTS_HEADER_WELSH;

public class TrlPrsBilingual {
    private final HtmlGenerator htmlGenerator;
    private final PDFGenerationService pdfGenerationService;
    private final CvsMotCertificate testCertificate;
    private final PDFParser pdfParser;

    private PdfReader pdfReader;

    public TrlPrsBilingual() {
        this.testCertificate = CvsCertificateTestDataProvider.getCvsTrlPrsBilingual();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
        this.pdfParser = new PDFParser();
    }

    @Before
    public void setup() throws Exception {
        byte[] pdfData = pdfGenerationService.generate(htmlGenerator.generate(testCertificate));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyEnglishTitles() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("MOT test certificate (TRL)"));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains("Refusal of MOT test certificate"));
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("This vehicle has an outstanding recall"));
        assertTrue(pdfParser.getRawText(pdfReader, 3).contains("This vehicle has an outstanding recall"));
    }

    @Test
    public void verifyWelshTitles() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains("Tystysgrif prawf MOT (TRL)"));
        assertTrue(pdfParser.getRawText(pdfReader, 6).contains("Gwrthod tystysgrif prawf MOT"));
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains("Mae gan y cerbyd hwn wedi cael ei alw'n ôl"));
        assertTrue(pdfParser.getRawText(pdfReader, 6).contains("Mae gan y cerbyd hwn wedi cael ei alw'n ôl"));
    }

    @Test
    public void verifyBilingualMinorDefectsHeader() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(MINOR_DEFECTS_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains(MINOR_DEFECTS_HEADER_WELSH));
    }

    @Test
    public void verifyBilingualAdvisoryDefectsHeader() throws Exception {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(ADVISORIES_HEADER_TEXT));
        assertTrue(pdfParser.getRawText(pdfReader, 4).contains(ADVISORIES_HEADER_TEXT_WELSH));
    }
}
