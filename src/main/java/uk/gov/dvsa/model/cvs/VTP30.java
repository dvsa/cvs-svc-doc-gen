package uk.gov.dvsa.model.cvs;

public class VTP30 extends CvsMotFailCertificate {

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Registration number";  }
}
