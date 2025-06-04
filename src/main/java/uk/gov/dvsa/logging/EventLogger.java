package uk.gov.dvsa.logging;

import org.apache.logging.log4j.Logger;
import uk.gov.dvsa.model.CurrentTracingInformation;

import java.text.NumberFormat;
import java.text.DecimalFormat;

public class EventLogger {

    private final Logger logger;
    private static final NumberFormat formatter = new DecimalFormat("#0.000000");

    public EventLogger(Logger logger) {
        this.logger = logger;
    }

    public void logEvent(EventType event) {
        LogContextWrapper.setEvent(event);
        logger.info(event.getDescription());
    }

    public void logEvent(EventType event, long durationNanos) {
        String duration = formatter.format(durationNanos / 10e9);
        LogContextWrapper.setEvent(event);
        LogContextWrapper.setDuration(duration);
        logger.info(event.getDescription());
        LogContextWrapper.cleanupDuration();
    }

    public void logError(EventType event, long durationNanos, Exception e) {
        String duration = formatter.format(durationNanos / 10e9);
        LogContextWrapper.setEvent(event);
        LogContextWrapper.setDuration(duration);
        logger.error(e.getMessage(), e);
        LogContextWrapper.cleanupDuration();
    }

    public void logEventWithTraceInfo(EventType eventType, CurrentTracingInformation currentTracingInformation) {
        LogContextWrapper.setTraceInformation(
                currentTracingInformation.getTraceId(),
                currentTracingInformation.getSpanId(),
                currentTracingInformation.getParentSpanId()
        );
        logEvent(eventType);
    }
}
