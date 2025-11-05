package uk.gov.dvsa.model.cvs;

public class CvsTrlPRS extends CvsMotFailCertificate {

    public String getTestType() {
        return "TRL";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5A";
    }

    public String getPresentedDocumentNameFail() {
        return "VTG30";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Identification number";  }
}
