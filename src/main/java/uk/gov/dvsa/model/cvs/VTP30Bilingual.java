package uk.gov.dvsa.model.cvs;

public class VTP30Bilingual extends VTP30W {

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNameFail() {
        return "VTP30";
    }

    public String getPresentedDocumentNameFailWelsh() {
        return "VTP30W";
    }

    public String getVersionNumberFail() {
        return "1.1";
    }

    public String getVersionNumberFailWelsh() {
        return "1.1";
    }

    public String getRegOrIdHeading() {
        return "Registration number";
    }

    public String getRegOrIdHeadingWelsh() {
        return "Rhif cofrestru";
    }
}
