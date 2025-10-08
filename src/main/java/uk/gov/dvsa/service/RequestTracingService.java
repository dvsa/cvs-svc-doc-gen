package uk.gov.dvsa.service;

import uk.gov.dvsa.model.CurrentTracingInformation;

import java.util.UUID;

public class RequestTracingService {

    private static final RequestTracingService instance = new RequestTracingService();
    private static final String HEX_FORMAT = "%016x";

    private CurrentTracingInformation currentTracingInformation;

    private RequestTracingService() {
        currentTracingInformation = new CurrentTracingInformation();
    }

    public static RequestTracingService getInstance() {
        return instance;
    }

    public void setCurrentTracingInformation(String traceId, String spanId, String parentSpanId) {
        currentTracingInformation.setTraceId(traceId);
        currentTracingInformation.setSpanId(spanId);
        currentTracingInformation.setParentSpanId(parentSpanId);
    }

    public CurrentTracingInformation getCurrentTracingInformation() {
        return currentTracingInformation;
    }

    public String generateUUID() {
        return String.format(HEX_FORMAT, UUID.randomUUID().getMostSignificantBits());
    }
}
