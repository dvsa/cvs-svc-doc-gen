package uk.gov.dvsa.view;

import org.apache.commons.lang3.StringUtils;
import uk.gov.dvsa.model.cvs.certificateData.CvsOdometerReading;

import java.util.Optional;

public class CvsOdometerReadingFormatter {

    public static final String MILES = "miles";
    public static final String MILES_WELSH = "milltiroedd";
    private static final String MILES_UNIT = "mi";
    public static final String KILOMETERS = "kilometres";
    public static final String KILOMETERS_WELSH = "cilometrau";
    private static final String KILOMETERS_UNIT = "km";
    private static final String THOUSANDS_PATTERN = "\\B(?=(?:.{3})+$)";
    private static final String THOUSANDS_SEPARATOR = ",";

    public String formatValue(CvsOdometerReading odometer) {
        if (odometer == null) {
            return "";
        }

        if (isRemark(odometer)) {
            return odometer.getValue();
        }

        return formatNumberValue(odometer.getValue()) +
            Optional.ofNullable(odometer.getUnit())
            .map(unit -> " " + formatUnit(unit, false))
            .orElse("");
    }

    public String formatWelshValue(CvsOdometerReading odometer) {
        if (odometer == null) {
            return "";
        }

        if (isRemark(odometer)) {
            return odometer.getValue();
        }

        return formatNumberValue(odometer.getValue()) +
                Optional.ofNullable(odometer.getUnit())
                        .map(unit -> " " + formatUnit(unit, true))
                        .orElse("");
    }

    private String formatUnit(String unit, Boolean unitWelsh) {
        switch (unit) {
            case MILES:
            case MILES_UNIT:
                return unitWelsh.equals(Boolean.TRUE) ? MILES_WELSH : MILES;
            case KILOMETERS:
            case KILOMETERS_UNIT:
                return unitWelsh.equals(Boolean.TRUE) ? KILOMETERS_WELSH : KILOMETERS;
            default:
                return unit;
        }
    }

    private String formatNumberValue(String value) {
        return value.replaceAll(THOUSANDS_PATTERN, THOUSANDS_SEPARATOR);
    }

    private boolean isRemark(CvsOdometerReading odometer) {
        return !StringUtils.isNumeric(odometer.getValue());
    }
}
