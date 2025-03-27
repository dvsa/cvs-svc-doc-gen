package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.MSVA30;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;


import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MSVA30Tests {
    private static final String CERT_NAME = "MOTORCYCLE SINGLE VEHICLE APPROVAL (MSVA)";

    private final String FOOTER_DOC_NAME = "MSVA30VTA (DVSA0848)";

    private final String FOOTER_VERSION_DATE = "Version 1.2 July 2024";

    private PDFGenerationService pdfGenerationService;
    private HtmlGenerator htmlGenerator;
    private PDFParser pdfParser;
    private MSVA30 msva30;
    private PdfReader pdfReader;
    private byte[] pdfData;

    public MSVA30Tests() {
        this.msva30 = CvsCertificateTestDataProvider.getMSVA30();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws IOException {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(msva30));
        pdfReader = pdfParser.readPdf(pdfData);
    }

    @Test
    public void verifyTitleIsOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(CERT_NAME));
    }

    @Test
    public void verifyFormNameInFooterOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(FOOTER_DOC_NAME));
    }

    @Test
    public void verifyPageCounterOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("1 of 2"));
    }

    @Test
    public void verifyPageCounterAtTopLeftOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("Page 1 of 2"));
    }

    @Test
    public void verifyVersionNumberInFooterOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(FOOTER_VERSION_DATE));
    }

    @Test
    public void verifyFormNameInFooterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(FOOTER_DOC_NAME));
    }

    @Test
    public void verifyPageCounterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains("2 of 2"));
    }

    @Test
    public void verifyVersionNumberInFooterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(FOOTER_VERSION_DATE));
    }
}
