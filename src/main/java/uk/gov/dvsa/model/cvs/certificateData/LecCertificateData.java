package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LecCertificateData {
    @JsonProperty("Make")
    private String make;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("SerialNumber")
    private String serialNumber;

    @JsonProperty("ExpiryDate")
    private String expiryDate;

    @JsonProperty("VRM")
    private String vrm;

    @JsonProperty("VIN")
    private String vin;

    @JsonProperty("PrescribedEmissionStandard")
    private String prescribedEmissionStandard;

    @JsonProperty("ParticulateTrapFitted")
    private String particulateTrapFitted;

    @JsonProperty("ParticulateTrapSerialNumber")
    private String particulateTrapSerialNumber;

    @JsonProperty("ModificationTypeUsed")
    private String modificationTypeUsed;

    @JsonProperty("ModificationType")
    private String modificationType;

    @JsonProperty("SmokeTestLimit")
    private String smokeTestLimit;

    @JsonProperty("AdditionalNotesRequired")
    private String additionalNotes;

    @JsonProperty("DateOfTheTest")
    private String testDate;

    @JsonProperty("TestStationName")
    private String testStationName;

    @JsonProperty("TestStationPNumber")
    private String testStationPNumber;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate.replace("-", "/").replace(".", "/");
    }

    public String getVrm() {
        return vrm;
    }
    public boxChar[] getVrmArray() {
        return boxify(this.vrm,7);
    }

    public void setVrm(String vrm) {
        this.vrm = vrm;
    }

    public String getVin() {
        return vin;
    }
    public boxChar[] getVinArray() {
        return boxify(this.vin,17);
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPrescribedEmissionStandard() {
        return prescribedEmissionStandard;
    }

    public void setPrescribedEmissionStandard(String prescribedEmissionStandard) {
        this.prescribedEmissionStandard = prescribedEmissionStandard;
    }

    public String getParticulateTrapFitted() {
        return particulateTrapFitted;
    }

    public void setParticulateTrapFitted(String particulateTrapFitted) {
        this.particulateTrapFitted = particulateTrapFitted;
    }

    public String getParticulateTrapSerialNumber() {
        return particulateTrapSerialNumber;
    }

    public void setParticulateTrapSerialNumber(String particulateTrapSerialNumber) {
        this.particulateTrapSerialNumber = particulateTrapSerialNumber;
    }

    public String getModificationTypeUsed() {
        return modificationTypeUsed;
    }

    public void setModificationTypeUsed(String modificationTypeUsed) {
        this.modificationTypeUsed = modificationTypeUsed;
    }

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType.toUpperCase();
    }
    public boolean getModificationTypeP() {
        return this.modificationType.equals("P");
    }
    public boolean getModificationTypeM() {
        return this.modificationType.equals("M");
    }
    public boolean getModificationTypeG() {
        return this.modificationType.equals("G");
    }

    public String getSmokeTestLimit() {
        return smokeTestLimit;
    }

    public void setSmokeTestLimit(String smokeTestLimit) {
        this.smokeTestLimit = smokeTestLimit;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public notesLines[] getAdditionalNotesLines() {
        return splitLines(this.additionalNotes, 4, 90);
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate.replace("-", "/");
    }

    public String getTestStationName() {
        return testStationName;
    }

    public void setTestStationName(String testStationName) {
        this.testStationName = testStationName;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public void setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
    }

    public String getParticulateTrapFittedOrModificationTypeUsed() {
        return "P".equals(this.modificationType) ? this.particulateTrapFitted : this.modificationTypeUsed;
    }

    private boxChar[] boxify(String string, int length) {
        boxChar[] boxCharArray = new boxChar[length];
        for(int i = 0; i < boxCharArray.length; i++) {
            boxCharArray[i] = new boxChar();
            if (i < string.length()) {
                boxCharArray[i].letter = string.charAt(i);
            }
        }
        return boxCharArray;
    }

    private notesLines[] splitLines(String string, int numLines, int lineLength) {
        notesLines[] notesLinesArray = new  notesLines[numLines];
        for(int i = 0; i < notesLinesArray.length; i++) {
            notesLinesArray[i] = new notesLines();
            if (string.length() < lineLength) {
                notesLinesArray[i].line = string;
                string = "";
            } else {
                int splitIndex = 0;
                int spaceIndex = string.indexOf(" ");
                while (spaceIndex >= 0 && splitIndex < lineLength) {
                    splitIndex = spaceIndex;
                    spaceIndex = string.indexOf(" ", spaceIndex + 1);
                }
                notesLinesArray[i].line = string.substring(0, splitIndex);
                string = string.substring(splitIndex + 1);
            }
        }
        return notesLinesArray;
    }
}

class boxChar {
    public char letter = Character.MIN_VALUE;

    public char getLetter() { return this.letter; }
}

class notesLines {
    public String line = "";

    public String getLine() {
        return line;
    }
}
