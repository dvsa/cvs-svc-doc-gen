package pdfverification.tests;

import com.github.jknack.handlebars.Handlebars;
import com.itextpdf.text.pdf.PdfReader;
import htmlverification.service.CvsCertificateTestDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdfverification.service.PDFParser;
import uk.gov.dvsa.model.cvs.MinistryPlate;
import uk.gov.dvsa.service.HtmlGenerator;
import uk.gov.dvsa.service.PDFGenerationService;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MinistryPlateTests {
    protected HtmlGenerator htmlGenerator;
    protected PDFGenerationService pdfGenerationService;
    protected MinistryPlate testCertificate;
    protected PDFParser pdfParser;
    protected PdfReader pdfReader;
    protected byte[] pdfData;

    public MinistryPlateTests() {
        this.htmlGenerator = new HtmlGenerator(new Handlebars());
        this.pdfParser = new PDFParser();
        this.pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Before
    public void setup() throws Exception {
        pdfData = pdfGenerationService.generate(htmlGenerator.generate(CvsCertificateTestDataProvider.getMinistryPlate()));
        pdfReader = pdfParser.readPdf(pdfData);

        try (FileOutputStream fos = new FileOutputStream("ministryPlate.pdf")) {
            fos.write(pdfData);
        }
    }

    @Test
    public void verifyTitle() throws IOException {
//       assertTrue(pdfParser.getRawText(pdfReader, 1).contains("this will fail"));
    }


}
