package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Defects {
    @JsonProperty("DangerousDefects")
    private String[] dangerousDefects;
    @JsonProperty("MajorDefects")
    private String[] majorDefects;
    @JsonProperty("MinorDefects")
    private String[] minorDefects;
    @JsonProperty("AdvisoryDefects")
    private String[] advisoryDefects;
    @JsonProperty("PRSDefects")
    private String[] pRSDefects;
    private DefectTitleLocation defectTitleLocation;

    public Defects() {}

    @JsonCreator
    public Defects(
            @JsonProperty("DangerousDefects") String[] dangerousDefects,
            @JsonProperty("MajorDefects") String[] majorDefects,
            @JsonProperty("MinorDefects") String[] minorDefects,
            @JsonProperty("AdvisoryDefects") String[] advisoryDefects,
            @JsonProperty("PRSDefects") String[] pRSDefects
    ) {
        this.dangerousDefects = dangerousDefects;
        this.majorDefects = majorDefects;
        this.minorDefects = minorDefects;
        this.advisoryDefects = advisoryDefects;
        this.pRSDefects = pRSDefects;
        this.setDefectHeading();
    }
    public void setDefectHeading() {
        this.defectTitleLocation = DefectTitleLocation.DangerousDefects;
        if (this.dangerousDefects.length == 0) {
            this.defectTitleLocation = DefectTitleLocation.MajorDefects;
        } else if (this.majorDefects.length == 0) {
            this.defectTitleLocation = DefectTitleLocation.MinorDefects;
        } else if (this.minorDefects.length == 0) {
            this.defectTitleLocation = DefectTitleLocation.AdvisoryDefects;
        } else if (this.advisoryDefects.length == 0) {
            this.defectTitleLocation = DefectTitleLocation.PRSDefects;
        }
    }

    public String[] getDangerousDefects() {
        return dangerousDefects;
    }

    public boolean getDangerousDefectsExist() {
        return this.dangerousDefects.length > 0;
    }

    public String[] getMajorDefects() {
        return majorDefects;
    }

    public boolean getMajorDefectsExist() {
        return this.majorDefects.length > 0;
    }

    public boolean getMajorDefectsIsFirst() {
        return this.defectTitleLocation == DefectTitleLocation.MajorDefects;
    }

    public String[] getMinorDefects() {
        return minorDefects;
    }

    public boolean getMinorDefectsExist() {
        return this.minorDefects.length > 0;
    }

    public boolean getMinorDefectsIsFirst() {
        return this.defectTitleLocation == DefectTitleLocation.MinorDefects;
    }

    public  String[] getAdvisoryDefects() {
        return advisoryDefects;
    }

    public boolean getAdvisoryDefectsExist() {
        return this.advisoryDefects.length > 0;
    }

    public boolean getAdvisoryDefectsIsFirst() {
        return this.defectTitleLocation == DefectTitleLocation.AdvisoryDefects;
    }

    public String[] getPRSDefects() {
        return pRSDefects;
    }

    public boolean getPRSDefectsExist() {
        return this.pRSDefects.length > 0;
    }

    public boolean getPRSDefectsIsFirst() {
        return this.defectTitleLocation == DefectTitleLocation.PRSDefects;
    }

    public boolean getDefectsExist() {
        return this.dangerousDefects.length > 0 ||
                this.majorDefects.length > 0 ||
                this.minorDefects.length > 0 ||
                this.advisoryDefects.length > 0 ||
                this.pRSDefects.length > 0;
    }


}
