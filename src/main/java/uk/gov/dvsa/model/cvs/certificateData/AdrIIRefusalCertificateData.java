package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdrIIRefusalCertificateData {

    @JsonProperty("vrm")
    private String vrm;

    @JsonProperty("vin")
    private String vin;

    @JsonProperty("testNumber")
    private String testNumber;

    // Reason 1: does not meet the required roadworthiness standard
    @JsonProperty("failureReasonRoadworthiness")
    private boolean failureReasonRoadworthiness;

    // Reason 2: does not comply with the special requirements of Part 9
    @JsonProperty("failureReasonSpecialRequirements")
    private boolean failureReasonSpecialRequirements;

    // Free text detailing the reason(s) the ADR test failed (appears in the notes box)
    @JsonProperty("notes")
    private String notes;

    @JsonProperty("date")
    private String date;

    public String getVrm() {
        return vrm;
    }

    public AdrIIRefusalCertificateData setVrm(String vrm) {
        this.vrm = vrm;
        return this;
    }

    public String getVin() {
        return vin;
    }

    public AdrIIRefusalCertificateData setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public AdrIIRefusalCertificateData setTestNumber(String testNumber) {
        this.testNumber = testNumber;
        return this;
    }

    public boolean getFailureReasonRoadworthiness() {
        return failureReasonRoadworthiness;
    }

    public AdrIIRefusalCertificateData setFailureReasonRoadworthiness(boolean failureReasonRoadworthiness) {
        this.failureReasonRoadworthiness = failureReasonRoadworthiness;
        return this;
    }

    public boolean getFailureReasonSpecialRequirements() {
        return failureReasonSpecialRequirements;
    }

    public AdrIIRefusalCertificateData setFailureReasonSpecialRequirements(boolean failureReasonSpecialRequirements) {
        this.failureReasonSpecialRequirements = failureReasonSpecialRequirements;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AdrIIRefusalCertificateData setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public boolean getHasNotes() {
        return this.notes != null && !this.notes.isEmpty();
    }

    public String getDate() {
        return date;
    }

    public AdrIIRefusalCertificateData setDate(String date) {
        this.date = date;
        return this;
    }
}
