package uk.gov.dvsa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.cvs.certificateData.Reissue;

public abstract class Document {

    private String documentName;

    @JsonProperty("Watermark")
    private String watermark;

    @JsonProperty("Reissue")
    private Reissue reissue;

    public String getWatermark() {
        return watermark;
    }

    public Document setWatermark(String watermark) {
        this.watermark = watermark;
        return this;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Document setDocumentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public Reissue getReissue() {
        return reissue;
    }

    public void setReissue(Reissue reissue) {
        this.reissue = reissue;
    }

    public String getRoot() {
        return System.getenv("LAMBDA_TASK_ROOT");
    }
}
