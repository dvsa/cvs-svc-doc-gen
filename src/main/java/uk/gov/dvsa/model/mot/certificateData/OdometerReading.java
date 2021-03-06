package uk.gov.dvsa.model.mot.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class OdometerReading {
    @JsonProperty
    private String value;

    @JsonProperty
    private String unit;

    @JsonProperty
    private LocalDate date;

    @JsonProperty
    private String valueCy;

    public OdometerReading() {
    }

    public OdometerReading(String value, String unit, LocalDate date) {
        this.value = value;
        this.unit = unit;
        this.date = date;
    }

    public OdometerReading(String value, String valueCy, String unit, LocalDate date) {
        this.value = value;
        this.valueCy = valueCy;
        this.unit = unit;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public OdometerReading setValue(String value) {
        this.value = value;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public OdometerReading setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OdometerReading setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getValueCy() {
        return valueCy;
    }

    public OdometerReading setValueCy() {
        this.valueCy = valueCy;
        return this;
    }
}
