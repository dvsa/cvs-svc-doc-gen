package uk.gov.dvsa.model.cvs;

public class VTG30 extends CvsMotFailCertificate {

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTG30";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Identification number";  }
}
