package uk.gov.dvsa;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import uk.gov.dvsa.exception.HttpException;
import uk.gov.dvsa.logging.EventLogger;
import uk.gov.dvsa.logging.EventType;
import uk.gov.dvsa.model.ApiGatewayResponse;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.service.HtmlGeneratorFactory;
import uk.gov.dvsa.service.PDFGenerationService;
import uk.gov.dvsa.service.RequestParser;
import uk.gov.dvsa.service.RequestTracingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uk.gov.dvsa.service.RequestParser.REQUEST_PARENT_SPAN_ID_HEADER;
import static uk.gov.dvsa.service.RequestParser.REQUEST_SPAN_ID_HEADER;
import static uk.gov.dvsa.service.RequestParser.REQUEST_TRACE_ID_HEADER;

public class PdfGenerator implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger logger = LogManager.getLogger(PdfGenerator.class);

    private final EventLogger eventLogger = new EventLogger(logger);

    private RequestParser requestParser;
    private HtmlGeneratorFactory htmlGeneratorFactory;
    private RequestTracingService requestTracingService;

    public PdfGenerator() {
        eventLogger.logEvent(EventType.CERT_LAMBDA_START);

        this.requestParser = new RequestParser();
        this.htmlGeneratorFactory = new HtmlGeneratorFactory();
        this.requestTracingService = RequestTracingService.getInstance();
    }

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        long start = System.nanoTime();
        eventLogger.logEvent(EventType.CERT_REQUEST_RECEIVED);

        System.out.println("*** This is the input from the handleRequest method: ");
        System.out.println(input);

        try {
            if (input.containsKey(requestParser.REQUEST_HEADERS)) {
            // Trace headers only they are provided in the input request, else ignore.
                Map<String, String> requestHeaders = requestParser.getTracingHeaders(input);
                requestTracingService.setCurrentTracingInformation(
                        requestHeaders.getOrDefault(REQUEST_TRACE_ID_HEADER, requestTracingService.generateUUID()),
                        requestHeaders.getOrDefault(REQUEST_SPAN_ID_HEADER, requestTracingService.generateUUID()),
                        requestHeaders.get(REQUEST_PARENT_SPAN_ID_HEADER)
                );

                eventLogger.logEventWithTraceInfo(EventType.CERT_SERVER_RECEIVE, requestTracingService.getCurrentTracingInformation());
            }
            Document document = requestParser.parseRequest(input);
            List<String> html = htmlGeneratorFactory.create(document.getDocumentName()).generate(document);

            byte [] binaryBody = new PDFGenerationService(new ITextRenderer()).generate(html);

            Map<String, String> responseHeaders = new HashMap<>();
            responseHeaders.put("Content-Type", "text/plain");

            ApiGatewayResponse response = ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setBinaryBody(binaryBody)
                .setHeaders(responseHeaders)
                .build();

            long duration = System.nanoTime() - start;
            eventLogger.logEvent(EventType.CERT_PROCESSED_SUCCESSFULLY, duration);
            eventLogger.logEvent(EventType.CERT_SERVER_SEND, duration);

            return response;
        } catch(Exception e) {
            int statusCode = e instanceof HttpException ? ((HttpException) e).getHttpCode() : 500;
            ApiGatewayResponse response = ApiGatewayResponse.builder()
                .setStatusCode(statusCode)
                .setRawBody(e.getMessage())
                .build();
            long duration = System.nanoTime() - start;
            eventLogger.logError(EventType.CERT_PROCESSED_ERRONEOUSLY, duration, e);
            return response;
        } finally {
            ThreadContext.clearAll();
        }
    }
}
