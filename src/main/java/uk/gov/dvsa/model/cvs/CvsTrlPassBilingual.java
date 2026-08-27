package uk.gov.dvsa.model.cvs;

public class CvsTrlPassBilingual extends VTG5AW {

    public String getTestType() {
        return "Trailer";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5A";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTG5AW";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getVersionNumberPassWelsh() {
        return "1.1";
    }

    public String getRegOrIdHeading() {
        return "Identification number";
    }
}
