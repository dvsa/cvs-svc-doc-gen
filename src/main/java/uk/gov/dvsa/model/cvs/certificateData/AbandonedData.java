package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbandonedData {
    @JsonProperty("RegistrationNumber")
    private String registrationNumber;
    @JsonProperty("ReasonsForRefusal")
    private String[] reasonsForRefusal;
    @JsonProperty("TestStationName")
    private String testStationName;
    @JsonProperty("TestStationPNumber")
    private String testStationPNumber;
    @JsonProperty("IssuersName")
    private String issuersName;
    @JsonProperty("AdditionalComments")
    private String additionalComments;
    @JsonProperty("DateOfTheTest")
    private String dateOfTheTest;
    @JsonProperty("Defects")
    private Defects defects;

    public AbandonedData() {
    }

    public AbandonedData(String registrationNumber,
                         String[] reasonsForRefusal,
                         String dateOfTheTest,
                         String testStationName,
                         String testStationPNumber,
                         String issuersName,
                         String additionalComments,
                         Defects defects) {
        this.registrationNumber = registrationNumber;
        this.reasonsForRefusal = reasonsForRefusal;
        this.dateOfTheTest = dateOfTheTest;
        this.testStationName = testStationName;
        this.testStationPNumber = testStationPNumber;
        this.issuersName = issuersName;
        this.additionalComments = additionalComments;
        this.defects = defects;
    }

    public String[] getRegistrationNumber() {
        return registrationNumber.split("");
    }

    public String[] getReasonsForRefusal() {
        return reasonsForRefusal;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public String getIssuersName() {
        return issuersName;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public String getDateOfTheTest() {
        return dateOfTheTest;
    }

    public Defects getDefects() {
        return defects;
    }
}
