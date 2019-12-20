package uk.gov.dvsa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.gov.dvsa.exception.HttpException;
import uk.gov.dvsa.logging.EventType;
import uk.gov.dvsa.logging.LoggingExecutor;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.mot.enums.DocumentsConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class RequestParser {

    private static final String PATH_PARAMETERS = "pathParameters";
    private static final String DOCUMENT_NAME_PARAMETER = "documentName";
    private static final String DOCUMENT_DIRECTORY_PARAMETER = "documentDirectory";
    private static final String REQUEST_BODY = "body";
    public static final String REQUEST_HEADERS = "headers";
    public static final String REQUEST_TRACE_ID_HEADER = "x-b3-traceid";
    public static final String REQUEST_SPAN_ID_HEADER = "x-b3-spanid";
    public static final String REQUEST_PARENT_SPAN_ID_HEADER = "x-b3-parentspanid";

    private static final Logger logger = LogManager.getLogger(RequestParser.class);

    private final LoggingExecutor executor = new LoggingExecutor(logger);

    static {
        HashMap<String, Class<? extends Document>> documentsMap = new HashMap<>();
    }

    private ObjectMapper om;

    public RequestParser() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
    }

    public Document parseRequest(Map<String, Object> input) {
        return executor.timed(() -> parse(input), EventType.CERT_REQUEST_PARSING);
    }

    private Document parse(Map<String, Object> input) {
        try {
            String documentName = readDocumentName(input);
            Class<? extends Document> documentType = readDocumentType(documentName);
            String documentJson = readRequestBody(input);
            Document document = om.readValue(documentJson, documentType);
            document.setDocumentName(documentName);
            return document;
        } catch (IOException e) {
            System.out.println("input event: " + input.toString());
            throw new HttpException.BadRequestException("Error parsing parameters", e);
        }
    }

    private Class<? extends Document> readDocumentType(String documentName) {
        return DocumentsConfig.fromDocumentName(documentName).getBaseClass();
    }

    private static String readDocumentName(Map<String, Object> input) {
        if (!input.containsKey(PATH_PARAMETERS)) {
            throw new HttpException.BadRequestException("Required lambda parameter " + PATH_PARAMETERS + " not found");
        }

        @SuppressWarnings("unchecked")
        Map<String, String> pathParameters = (Map<String, String>) input.get(PATH_PARAMETERS);

        if (!pathParameters.containsKey(DOCUMENT_NAME_PARAMETER)) {
            throw new HttpException.BadRequestException("Required path parameter " + DOCUMENT_NAME_PARAMETER + " not found");
        }

        if (!pathParameters.containsKey(DOCUMENT_DIRECTORY_PARAMETER)) {
            throw new HttpException.BadRequestException("Required path parameter " + DOCUMENT_DIRECTORY_PARAMETER + " not found");
        }

        String documentName = pathParameters.get(DOCUMENT_NAME_PARAMETER);
        String documentDirectory = pathParameters.get(DOCUMENT_DIRECTORY_PARAMETER);

        return trimFileExtension(documentDirectory + "/" + documentName);
    }

    private static String trimFileExtension(String documentPath) {
        int dotPosition = documentPath.lastIndexOf('.');

        if (dotPosition > 0) {
            return documentPath.substring(0, dotPosition);
        }
        return documentPath;
    }


    private static String readRequestBody(Map<String, Object> input) {
        if (!input.containsKey(REQUEST_BODY)) {
            throw new HttpException.BadRequestException("Required lambda parameter " + REQUEST_BODY + " not found");
        }
        return (String) (input.get(REQUEST_BODY));
    }

    public Map<String, String> getTracingHeaders(Map<String, Object> input) {
        @SuppressWarnings("unchecked")
        Map<String, String> headersMap = (Map<String, String>) input.get(REQUEST_HEADERS);
        List<String> headerKeys = asList(REQUEST_PARENT_SPAN_ID_HEADER, REQUEST_SPAN_ID_HEADER, REQUEST_TRACE_ID_HEADER);

        return headersMap.entrySet()
                .stream()
                .filter(entry -> headerKeys.contains(entry.getKey().toLowerCase()))
                .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(), entry -> entry.getValue()));
    }
}
