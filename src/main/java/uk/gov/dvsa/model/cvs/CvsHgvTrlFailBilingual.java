package uk.gov.dvsa.model.cvs;

public class CvsHgvTrlFailBilingual extends VTG30W {

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTG30";
    }

    public String getPresentedDocumentNameFailWelsh() {
        return "VTG30W";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getVersionNumberFailWelsh() {
        return "1.1";
    }

    public String getRegOrIdHeading() {
        return "Identification number";
    }

    public String getRegOrIdHeadingWelsh() {
        return "Rhif adnabod";
    }
}
