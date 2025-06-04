package uk.gov.dvsa.model.cvs.certificateData;

public class FormattedOdometerReading {
    private final String value;
    private final String date;

    public FormattedOdometerReading(String value, String date) {
        this.value = value;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
