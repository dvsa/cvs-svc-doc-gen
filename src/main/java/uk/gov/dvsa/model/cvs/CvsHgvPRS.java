package uk.gov.dvsa.model.cvs;

public class CvsHgvPRS extends CvsMotFailCertificate {

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5";
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

    public String getRegOrIdHeading() { return "Registration number";  }
}
