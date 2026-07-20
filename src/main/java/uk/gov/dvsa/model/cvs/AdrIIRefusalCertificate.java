package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.AdrIIRefusalCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdrIIRefusalCertificate extends Document {
    @JsonProperty("ID")
    private String id;

    @JsonProperty("Signature")
    private Signature signature;

    @JsonProperty("ADR_RFR_DATA")
    protected AdrIIRefusalCertificateData adrData;

    public String getId() {
        return id;
    }

    public AdrIIRefusalCertificate setId(String id) {
        this.id = id;
        return this;
    }

    public Signature getSignature() {
        return signature;
    }

    public AdrIIRefusalCertificate setSignature(Signature signature) {
        this.signature = signature;
        return this;
    }

    public AdrIIRefusalCertificateData getAdrData() {
        return adrData;
    }

    public Document setAdrData(AdrIIRefusalCertificateData adrData) {
        this.adrData = adrData;
        return this;
    }
}
