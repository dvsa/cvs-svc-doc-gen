package uk.gov.dvsa.model.cvs;

public class CvsPsvPRS extends CvsMotFailCertificate {

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTP20";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Registration number";  }
}
