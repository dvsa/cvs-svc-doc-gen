package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.AbandonedData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

public abstract class AbandonedCertificate extends Document {
    protected String regulationText;
    protected String vehicleTypeText;
    protected String titleTextIncludingRollingHeaders;
    protected String sectionTextRef;
    protected String formNumber;
    @JsonProperty("ABANDONED_DATA")
    private AbandonedData data;
    @JsonProperty("Signature")
    private Signature signature;

    public String getRegulationText() { return this.regulationText; }

    public String getVehicleTypeText() { return this.vehicleTypeText; }

    public String getTitleTextIncludingRollingHeaders() {
        return this.titleTextIncludingRollingHeaders;
    }

    public String getSectionTextRef() { return this.sectionTextRef; }

    public String getFormNumber() { return formNumber; }

    public AbandonedData getData() {
        return data;
    }

    public Signature getSignature() {
        return signature;
    }

    public Document setData(AbandonedData data) {
        this.data = data;
        return this;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getDocumentType() {
        return this.getDocumentName().split("/")[1];
    }
}
