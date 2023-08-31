package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.mot.results.DefectsList;
import uk.gov.dvsa.model.mot.results.Summary;

import java.util.List;

public class CvsMotCertificateDataWelsh extends CvsMotCertificateData {

    public static final String EU_NUMBER_FOR_DEFECTS = "6";
    public static final String ADVISORIES_HEADER_WELSH = "Monitro a thrwsio os oes angen (cynghorol)";
    public static final String MINOR_DEFECTS_HEADER_WELSH = "Atgyweiriwch cyn gynted â phosibl (ddiffygion bach)";
    public static final String PASS_SUMMARY_HEADER_WELSH = "Llwyddo";
    public static final String PASS_WITH_DEFECTS_HEADER_WELSH = "Llwyddo gyda diffygion";
    public static final String TESTING_ORGANISATION_WELSH = "ASIANTAETH SAFONAU GYRWYR A CHERBYDAU";

    @JsonProperty("AdvisoryDefectsWelsh")
    private List<String> advisoryDefectsWelsh;

    @JsonProperty("MinorDefectsWelsh")
    private List<String> minorDefectsWelsh;

    public Summary getSummaryWelsh() {
        return new Summary(buildSummaryTitleWelsh());
    }

    private String buildSummaryTitleWelsh() {
        if (!hasMinorDefects(minorDefectsWelsh)) {
            return PASS_SUMMARY_HEADER_WELSH;
        } else {
            return PASS_WITH_DEFECTS_HEADER_WELSH;
        }
    }

    public List<String> getAdvisoryDefectsWelsh() {
        return advisoryDefectsWelsh;
    }

    public CvsMotCertificateDataWelsh setAdvisoryDefectsWelsh(List<String> advisoryDefectsWelsh) {
        this.advisoryDefectsWelsh = advisoryDefectsWelsh;
        return this;
    }

    public List<String> getMinorDefectsWelsh() {
        return minorDefectsWelsh;
    }

    public CvsMotCertificateDataWelsh setMinorDefectsWelsh(List<String> minorDefectsWelsh) {
        this.minorDefectsWelsh = minorDefectsWelsh;
        return this;
    }

    public DefectsList getAdvisoryWelsh() {
        return new DefectsList(ADVISORIES_HEADER_WELSH, this.advisoryDefectsWelsh);
    }

    public DefectsList getMinorWelsh() {
        return new DefectsList(MINOR_DEFECTS_HEADER_WELSH, this.minorDefectsWelsh, EU_NUMBER_FOR_DEFECTS);
    }

    public String getTestingOrganisationWelsh() {
        return TESTING_ORGANISATION_WELSH;
    }
}
