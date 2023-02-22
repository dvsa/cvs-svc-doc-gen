package uk.gov.dvsa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.TrailerIntoService;
import uk.gov.dvsa.model.mot.VT30;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RequestParserTest {
    private RequestParser parser = new RequestParser();

    @Test
    public void parseMotRequest() throws IOException {
        Map<String, Object> input = readLambdaInput("/lambdaInput.json");

        Document document = parser.parseRequest(input);

        assertEquals(document.getDocumentName(), "MOT/VT30");
        assertEquals(((VT30)document).getData().getTestNumber(), "POC123");
        assertEquals(((VT30)document).getData().getDateOfTheTest(), LocalDate.of(2017, 11, 20));
    }

    @Test
    public void parseTrailerIntoServiceRequest() throws IOException {
        Map<String, Object> input = readLambdaInput("/lambdaInput_TrailerIntoService.json");

        Document document = parser.parseRequest(input);

        assertEquals(document.getDocumentName(), "CVS/TrailerIntoService");
        assertEquals(((TrailerIntoService)(document)).getLetterDateRequested(), "2023-02-17T18:15:00.000Z");
        assertEquals(((TrailerIntoService)(document)).getApprovalTypeNumber(), "APPROVAL/TYPE/**/NUMBER");
    }


    private static Map<String, Object> readLambdaInput(String resourceInput) throws IOException {
        return new ObjectMapper()
                .readValue(
                    RequestParserTest.class.getResource(resourceInput),
                        new TypeReference<HashMap<String, Object>>() {
                    }
                );
    }
}
