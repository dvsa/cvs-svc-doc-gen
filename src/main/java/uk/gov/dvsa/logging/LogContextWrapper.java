package uk.gov.dvsa.logging;

import org.apache.logging.log4j.ThreadContext;

public class LogContextWrapper {

    public static final String CONTEXT_EVENT_KEY = "event";
    public static final String CONTEXT_DURATION_KEY = "duration";
    public static final String PAGE_NUMBER_KEY = "pageNumber";
    private static final String CONTEXT_FORMATTED_DURATION_KEY = "formatted_duration";
    private static final String CONTEXT_TRACE_ID_KEY = "traceId";
    private static final String CONTEXT_SPAN_ID_KEY = "spanId";
    private static final String CONTEXT_PARENT_SPAN_ID_KEY = "parentSpanId";

    public static void setEvent(EventType event) {
        ThreadContext.put(CONTEXT_EVENT_KEY, event.getName());
    }

    public static void cleanupEvent() {
        ThreadContext.remove(CONTEXT_EVENT_KEY);
    }

    public static void setDuration(String duration) {
        ThreadContext.put(CONTEXT_DURATION_KEY, duration);
        ThreadContext.put(CONTEXT_FORMATTED_DURATION_KEY, duration + " seconds");
    }

    public static void cleanupDuration() {
        ThreadContext.remove(CONTEXT_DURATION_KEY);
        ThreadContext.remove(CONTEXT_FORMATTED_DURATION_KEY);
    }

    public static void setTraceInformation(String traceId, String spanId, String parentSpanId) {
        ThreadContext.put(CONTEXT_TRACE_ID_KEY, traceId);
        ThreadContext.put(CONTEXT_SPAN_ID_KEY, spanId);
        ThreadContext.put(CONTEXT_PARENT_SPAN_ID_KEY, parentSpanId);
    }

    public static void setSpanId(String spanId) {
        ThreadContext.put(CONTEXT_SPAN_ID_KEY, spanId);
    }

    public static void setParentSpanId(String parentSpanId) {
        ThreadContext.put(CONTEXT_PARENT_SPAN_ID_KEY, parentSpanId);
    }

    public static void setPageNumber(String pageNumber) {
        ThreadContext.put(PAGE_NUMBER_KEY, pageNumber);
    }

    public static void cleanupPageNumber() {
        ThreadContext.remove(PAGE_NUMBER_KEY);
    }
}
