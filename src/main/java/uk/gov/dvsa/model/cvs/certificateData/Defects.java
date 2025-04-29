package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Defects {
    @JsonProperty("DangerousDefects")
    private String[] dangerousDefectsList;
    @JsonProperty("MajorDefects")
    private String[] majorDefectsList;
    @JsonProperty("MinorDefects")
    private String[] minorDefectsList;
    @JsonProperty("AdvisoryDefects")
    private String[] advisoryDefectsList;
    @JsonProperty("PRSDefects")
    private String[] pRSDefectsList;

    private Defect dangerousDefect;
    private Defect majorDefect;
    private Defect minorDefect;
    private Defect advisoryDefect;
    private Defect pRSDefect;

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
        this.dangerousDefect = new Defect(
                dangerousDefects != null ? dangerousDefects : new String[0],
                titleLocation.equals(DefectTitleLocation.DangerousDefects)
        );
        this.majorDefect = new Defect(
                majorDefects != null ? majorDefects : new String[0],
                titleLocation.equals(DefectTitleLocation.MajorDefects)
        );
        this.minorDefect = new Defect(
                minorDefects != null ? minorDefects : new String[0],
                titleLocation.equals(DefectTitleLocation.MinorDefects)
        );
        this.advisoryDefect = new Defect(
                advisoryDefects != null ? advisoryDefects : new String[0],
                titleLocation.equals(DefectTitleLocation.AdvisoryDefects)
        );
        this.pRSDefect = new Defect(
                pRSDefects != null ? pRSDefects : new String[0],
                titleLocation.equals(DefectTitleLocation.PRSDefects)
        );
    }
    public DefectTitleLocation setDefectHeading(
            String[] dangerousDefects,
            String[] majorDefects,
            String[] minorDefects,
            String[] advisoryDefects,
            String[] pRSDefects
    ) {
        if (dangerousDefects != null && dangerousDefects.length > 0) {
            return DefectTitleLocation.DangerousDefects;
        } else if (majorDefects != null && majorDefects.length > 0) {
            return DefectTitleLocation.MajorDefects;
        } else if (minorDefects != null && minorDefects.length > 0) {
            return DefectTitleLocation.MinorDefects;
        } else if (advisoryDefects != null && advisoryDefects.length > 0) {
            return DefectTitleLocation.AdvisoryDefects;
        } else if (pRSDefects != null && pRSDefects.length > 0) {
            return DefectTitleLocation.PRSDefects;
        }
        return DefectTitleLocation.DangerousDefects;
    }

    public Defect getDangerousDefects() {
        return dangerousDefect;
    }

    public Defect getMajorDefects() {
        return majorDefect;
    }

    public Defect getMinorDefects() {
        return minorDefect;
    }

    public Defect getAdvisoryDefects() {
        return advisoryDefect;
    }

    public Defect getPRSDefects() {
        return pRSDefect;
    }
}
