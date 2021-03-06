package uk.gov.dvsa.service;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class PDFGenerationServiceTest {

    private PDFGenerationService pdfGenerationService;

    @Before
    public void setUp() {
        pdfGenerationService = new PDFGenerationService(new ITextRenderer());
    }

    @Test
    public void checkIfValidHtmlIsConvertedToPdf() {
        List<String> validHtmlContent = new ArrayList<>();
        validHtmlContent.add(getHtmlContent());

        byte [] outputPdf = null;

        try {
            outputPdf = pdfGenerationService.generate(validHtmlContent);
        } catch (Exception e) {
             fail("An exception has been thrown!");
        }

        assertNotNull(outputPdf);
        assertNotEquals(0, outputPdf.length);
    }

    @Test
    public void checkIfInvalidHtmlResultsInExceptionBeingThrown() {
        List<String> invalidHtmlContent = new ArrayList<>();
        invalidHtmlContent.add("<htm");

        byte [] outputPdf = null;

        try {
            outputPdf = pdfGenerationService.generate(invalidHtmlContent);
            fail("An exception has not been thrown!");
        } catch (Exception e) {
        }

        assertNull(outputPdf);
    }

    private String getHtmlContent() {
        URL indexUrl = PDFGenerationServiceTest.class.getResource("/test-html.html");

        try {
            File indexContent = new File(indexUrl.toURI());
            return FileUtils.readFileToString(indexContent, "UTF-8");
        } catch (Exception e) {

        }
        return null;
    }
}
