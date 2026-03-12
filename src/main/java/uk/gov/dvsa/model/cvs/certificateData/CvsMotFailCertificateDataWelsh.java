package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CvsMotFailCertificateDataWelsh extends CvsMotFailCertificateData {
    public static final String FAILED_SUMMARY_HEADER_WELSH = "Methu";
    public static final String ADVISORIES_HEADER_WELSH = "Monitro a thrwsio os oes angen (cynghorol)";
    public static final String MINOR_DEFECTS_HEADER_WELSH = "Atgyweiriwch cyn gynted â phosibl (ddiffygion bach)";
    public static final String DANGEROUS_DEFECTS_HEADER_WELSH = "Peidiwch â gyrru nes ei fod wedi cael ei atgyweirio (diffygion peryglus)";
    public static final String MAJOR_DEFECT_HEADER_WELSH = "Atgyweirio ar unwaith (diffygion mawr)";
    public static final String PRS_DEFECTS_HEADER_WELSH = "Diffygion wedi'u cywiro ar adeg y prawf";
    public static final String TESTING_ORGANISATION_WELSH = "ASIANTAETH SAFONAU GYRWYR A CHERBYDAU";

    @JsonProperty("AdvisoryDefectsWelsh")
    private List<String> advisoryDefectsWelsh;

    @JsonProperty("MinorDefectsWelsh")
    private List<String> minorDefectsWelsh;

    @JsonProperty("DangerousDefectsWelsh")
    private List<String> dangerousDefectsWelsh;

    @JsonProperty("MajorDefectsWelsh")
    private List<String> majorDefectsWelsh;

    @JsonProperty("PRSDefectsWelsh")
    private List<String> prsDefectsWelsh;

    @JsonProperty("AdvisoryDefectsHeaderWelsh")
    private String advisoryDefectsHeaderWelsh;

    @JsonProperty("MinorDefectsHeaderWelsh")
    private String minorDefectsHeaderWelsh;

    @JsonProperty("DangerousDefectsHeaderWelsh")
    private String dangerousDefectsHeaderWelsh;

    @JsonProperty("MajorDefectsHeaderWelsh")
    private String majorDefectsHeaderWelsh;

    @JsonProperty("PrsDefectsHeaderWelsh")
    private String prsDefectsHeaderWelsh;

    public List<String> getAdvisoryDefectsWelsh() {
        return advisoryDefectsWelsh;
    }

    public List<String> getMinorDefectsWelsh() {
        return this.minorDefectsWelsh;
    }

    public List<String> getDangerousDefectsWelsh() {
        return this.dangerousDefectsWelsh;
    }

    public List<String> getMajorDefectsWelsh() {
        return this.majorDefectsWelsh;
    }

    public List<String> getPrsDefectsWelsh() {
        return this.prsDefectsWelsh;
    }

    public CvsMotFailCertificateDataWelsh setAdvisoryDefectsWelsh(List<String> advisoryDefectsWelsh) {
        this.advisoryDefectsWelsh = advisoryDefectsWelsh;
        return this;
    }

    public CvsMotFailCertificateDataWelsh setMinorDefectsWelsh(List<String> minorDefectsWelsh) {
        this.minorDefectsWelsh = minorDefectsWelsh;
        return this;
    }

    public CvsMotFailCertificateDataWelsh setDangerousDefectsWelsh(List<String> dangerousDefectsWelsh) {
        this.dangerousDefectsWelsh = dangerousDefectsWelsh;
        return this;
    }

    public CvsMotFailCertificateDataWelsh setMajorDefectsWelsh(List<String> majorDefectsWelsh) {
        this.majorDefectsWelsh = majorDefectsWelsh;
        return this;
    }

    public CvsMotFailCertificateDataWelsh setPrsDefectsWelsh(List<String> prsDefectsWelsh) {
        this.prsDefectsWelsh = prsDefectsWelsh;
        return this;
    }

    public DefectsList getAdvisoryWelsh() {
        return new DefectsList(ADVISORIES_HEADER_WELSH, this.advisoryDefectsWelsh);
    }


    public DefectsList getMinorWelsh() {
        return new DefectsList(MINOR_DEFECTS_HEADER_WELSH, this.minorDefectsWelsh, EU_NUMBER_FOR_DEFECTS);
    }

    public DefectsList getDangerousWelsh() {
        DefectsList dangerousDefectsWelsh = new DefectsList(DANGEROUS_DEFECTS_HEADER_WELSH, this.dangerousDefectsWelsh, EU_NUMBER_FOR_DEFECTS);
        return dangerousDefectsWelsh.setIsDangerous(true);
    }

    public DefectsList getMajorWelsh() {
        return new DefectsList(MAJOR_DEFECT_HEADER_WELSH, this.majorDefectsWelsh, EU_NUMBER_FOR_DEFECTS);
    }

    public DefectsList getPrsWelsh() {
        return new DefectsList(PRS_DEFECTS_HEADER_WELSH, this.prsDefectsWelsh, EU_NUMBER_FOR_DEFECTS);
    }

    public List<FormattedOdometerReading> getMileageHistoryWelsh() {
        if (null != getOdometerHistoryList()) {
            return getOdometerHistoryList().stream()
                    .map(
                            entry -> new FormattedOdometerReading(
                                    ODOMETER_FORMATTER.formatWelshValue(entry),
                                    entry.getDate()
                            )
                    )
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public String getFormattedCurrentOdometerWelsh() {
        return ODOMETER_FORMATTER.formatWelshValue(getCurrentOdometer());
    }

    public Summary getSummaryWelsh() {
        return new Summary(FAILED_SUMMARY_HEADER_WELSH);
    }

    public String getAdvisoryDefectsHeaderWelsh() {
        return advisoryDefectsHeaderWelsh;
    }

    public CvsMotFailCertificateDataWelsh setAdvisoryDefectsHeaderWelsh(String advisoryDefectsHeaderWelsh) {
        this.advisoryDefectsHeaderWelsh = advisoryDefectsHeaderWelsh;
        return this;
    }

    public String getMinorDefectsHeaderWelsh() {
        return minorDefectsHeaderWelsh;
    }

    public CvsMotFailCertificateDataWelsh setMinorDefectsHeaderWelsh(String getMinorDefectsHeaderWelsh) {
        this.minorDefectsHeaderWelsh = getMinorDefectsHeaderWelsh;
        return this;
    }

    public String getDangerousDefectsHeaderWelsh() {
        return dangerousDefectsHeaderWelsh;
    }

    public CvsMotFailCertificateDataWelsh setDangerousDefectsHeaderWelsh(String dangerousDefectsHeaderWelsh) {
        this.dangerousDefectsHeaderWelsh = dangerousDefectsHeaderWelsh;
        return this;
    }

    public String getMajorDefectsHeaderWelsh() {
        return majorDefectsHeaderWelsh;
    }

    public CvsMotFailCertificateDataWelsh setMajorDefectsHeaderWelsh(String majorDefectsHeaderWelsh) {
        this.majorDefectsHeaderWelsh = majorDefectsHeaderWelsh;
        return this;
    }

    public String getPrsDefectsHeaderWelsh() {
        return prsDefectsHeaderWelsh;
    }

    public CvsMotFailCertificateDataWelsh setPrsDefectsHeaderWelsh(String prsDefectsHeaderWelsh) {
        this.prsDefectsHeaderWelsh = prsDefectsHeaderWelsh;
        return this;
    }

    public String getTestingOrganisationWelsh() {
        return TESTING_ORGANISATION_WELSH;
    }
}
