package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.mot.certificateData.MotCertificateData;
import uk.gov.dvsa.model.mot.results.DefectsList;
import uk.gov.dvsa.model.mot.results.ReasonForCancel;
import uk.gov.dvsa.model.mot.results.Summary;

import java.util.List;

public class CvsMotFailCertificateData extends CvsMotCertificateData {

    public static final String FAILED_SUMMARY_HEADER = "Fail"; // header displayed above other lists of RFRs
    private static final String DANGEROUS_DEFECTS_HEADER = "Do not drive until repaired (dangerous defects)";
    private static final String MAJOR_DEFECTS_HEADER = "Repair immediately (major defects)";
    private static final String PRS_DEFECTS_HEADER = "Defects rectified at time of test";

    @JsonProperty("DangerousDefectsHeader")
    private String dangerousDefectsHeader;

    @JsonProperty("MajorDefectsHeader")
    private String majorDefectsHeader;

    @JsonProperty("DangerousDefects")
    private List<String> dangerousDefects;

    @JsonProperty("MajorDefects")
    private List<String> majorDefects;

    @JsonProperty("PRSDefects")
    private List<String> prsDefects;

    @JsonProperty("IsTrailer")
    private boolean isTrailer;

    public CvsMotFailCertificateData setDangerousDefects(List<String> dangerousDefects) {
        this.dangerousDefects = dangerousDefects;
        return this;
    }

    public List<String> getDangerousDefects() {
        return this.dangerousDefects;
    }

    public CvsMotFailCertificateData setMajorDefects(List<String> majorDefects) {
        this.majorDefects = majorDefects;
        return this;
    }

    public List<String> getMajorDefects() {
        return this.majorDefects;
    }

    public String getDangerousDefectsHeader() {
        return dangerousDefectsHeader;
    }

    public CvsMotFailCertificateData setDangerousDefectsHeader(String dangerousDefectsHeader) {
        this.dangerousDefectsHeader = dangerousDefectsHeader;
        return this;
    }

    public String getMajorDefectsHeader() {
        return majorDefectsHeader;
    }

    public CvsMotFailCertificateData setMajorDefectsHeader(String majorDefectsHeader) {
        this.majorDefectsHeader = majorDefectsHeader;
        return this;
    }

    public CvsMotFailCertificateData setPrsDefects(List<String> prsDefects) {
        this.prsDefects = prsDefects;
        return this;
    }

    public DefectsList getMajor() {
        return new DefectsList(MAJOR_DEFECTS_HEADER, this.majorDefects, EU_NUMBER_FOR_DEFECTS);
    }

    public DefectsList getDangerous() {
        DefectsList dangerousDefects = new DefectsList(DANGEROUS_DEFECTS_HEADER, this.dangerousDefects, EU_NUMBER_FOR_DEFECTS);
        return dangerousDefects.setIsDangerous(true);
    }

    public DefectsList getPrs() {
        return new DefectsList(PRS_DEFECTS_HEADER, this.prsDefects, EU_NUMBER_FOR_DEFECTS);
    }

    public Summary getSummary() {
        return new Summary(FAILED_SUMMARY_HEADER);
    }

    public boolean getIsTrailer(){
        return this.isTrailer;
    }

    public CvsMotFailCertificateData setIsTrailer(boolean isTrailerValue) {
        this.isTrailer = isTrailerValue;
        return this;
    }
}
