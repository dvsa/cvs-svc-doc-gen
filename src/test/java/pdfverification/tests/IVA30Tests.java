package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.IVA30;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;

public class IVA30Tests {
    private static final String CERT_NAME = "INDIVIDUAL VEHICLE APPROVAL (IVA)";
    private static final String FOOTER_DOC_NAME = "IVA30VTA (DVSA0842)";
    private static final String FOOTER_VERSION_DATE = "Version 1.2 July 2024";
    private PDFGenerationService pdfGenerationService;
    private HtmlGenerator htmlGenerator;
    private PDFParser pdfParser;
    private IVA30 iva30;
    private PdfReader pdfReader;
    private byte[] pdfData;

    public IVA30Tests() {
        this.iva30 = CvsCertificateTestDataProvider.getIVA30();
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws IOException {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(iva30));
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
    public void verifyVersionNumberInFooterOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains(FOOTER_VERSION_DATE));
    }

    @Test
    public void verifyPageCounterAtTopLeftOnPage1() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 1).contains("Page 1 of 2"));
    }

    @Test
    public void verifyFormNameInFooterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(FOOTER_DOC_NAME));
    }

    @Test
    public void verifyPageCounterInFooterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains("2 of 2"));
    }

    @Test
    public void verifyVersionNumberInFooterOnPage2() throws IOException {
        assertTrue(pdfParser.getRawText(pdfReader, 2).contains(FOOTER_VERSION_DATE));
    }
}
