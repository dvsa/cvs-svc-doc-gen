package uk.gov.dvsa.logging;

import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class LoggingExecutor {

    private final Logger logger;
    private static final NumberFormat formatter = new DecimalFormat("#0.000000");

    public LoggingExecutor(Logger logger)
    {
        this.logger = logger;
    }

    public <T> T timed(Supplier<T> operation, EventType event) {
        long start = System.nanoTime();
        logEvent(event);
        try {
            T result = operation.get();
            logEvent(event, "Done in {} seconds", start);
            return result;
        } catch (Exception e) {
            logEvent(event, "Failed after {} seconds", start);
            throw e;
        }
    }

    private void logEvent(EventType event, String message, long start) {
        String duration = formatter.format((System.nanoTime() - start) / 10e9);;
        LogContextWrapper.setEvent(event);
        LogContextWrapper.setDuration(duration);
        logger.info(message, duration);
        LogContextWrapper.cleanupDuration();
    }

    private void logEvent(EventType event) {
        LogContextWrapper.setEvent(event);
        logger.info(event.getDescription());
    }
}
