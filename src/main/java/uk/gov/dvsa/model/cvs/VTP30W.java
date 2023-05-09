package uk.gov.dvsa.model.cvs;

public class VTP30W extends CvsMotFailCertificate {

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30W";
    }

    public String getVersionNumberFail() {
        return "1.0";
    }

    public String getRegOrIdHeading() { return "Registration number";  }
}
