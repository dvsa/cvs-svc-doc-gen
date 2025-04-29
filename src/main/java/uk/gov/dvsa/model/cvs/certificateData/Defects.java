package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Defects {
    @JsonProperty("DangerousDefects")
    private Defect dangerousDefects;
    @JsonProperty("MajorDefects")
    private Defect majorDefects;
    @JsonProperty("MinorDefects")
    private Defect minorDefects;
    @JsonProperty("AdvisoryDefects")
    private Defect advisoryDefects;
    @JsonProperty("PRSDefects")
    private Defect pRSDefects;

    public Defects() {}

    @JsonCreator
    public Defects(
            @JsonProperty("DangerousDefects") String[] dangerousDefects,
            @JsonProperty("MajorDefects") String[] majorDefects,
            @JsonProperty("MinorDefects") String[] minorDefects,
            @JsonProperty("AdvisoryDefects") String[] advisoryDefects,
            @JsonProperty("PRSDefects") String[] pRSDefects
    ) {
        DefectTitleLocation titleLocation = this.setDefectHeading(dangerousDefects, majorDefects, minorDefects, advisoryDefects, pRSDefects);
        this.dangerousDefects = new Defect(dangerousDefects,
                titleLocation.equals(DefectTitleLocation.DangerousDefects));
        this.majorDefects = new Defect(majorDefects,
                titleLocation.equals(DefectTitleLocation.MajorDefects));
        this.minorDefects = new Defect(minorDefects,
                titleLocation.equals(DefectTitleLocation.MinorDefects));
        this.advisoryDefects = new Defect(advisoryDefects,
                titleLocation.equals(DefectTitleLocation.AdvisoryDefects));
        this.pRSDefects = new Defect(pRSDefects,
                titleLocation.equals(DefectTitleLocation.PRSDefects));
    }
    public DefectTitleLocation setDefectHeading(
            String[] dangerousDefects,
            String[] majorDefects,
            String[] minorDefects,
            String[] advisoryDefects,
            String[] pRSDefects
    ) {
        if (dangerousDefects.length > 0) {
            return DefectTitleLocation.DangerousDefects;
        } else if (majorDefects.length > 0) {
            return DefectTitleLocation.MajorDefects;
        } else if (minorDefects.length > 0) {
            return DefectTitleLocation.MinorDefects;
        } else if (advisoryDefects.length > 0) {
            return DefectTitleLocation.AdvisoryDefects;
        } else if (pRSDefects.length > 0) {
            return DefectTitleLocation.PRSDefects;
        }
        return DefectTitleLocation.DangerousDefects;
    }

    public Defect getDangerousDefects() {
        return dangerousDefects;
    }

    public Defect getMajorDefects() {
        return majorDefects;
    }

    public Defect getMinorDefects() {
        return minorDefects;
    }

    public Defect getAdvisoryDefects() {
        return advisoryDefects;
    }

    public Defect getPRSDefects() {
        return pRSDefects;
    }
}
