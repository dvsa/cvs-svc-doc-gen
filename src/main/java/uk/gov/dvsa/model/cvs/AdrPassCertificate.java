package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.AdrPassCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

public class AdrPassCertificate extends Document {
    @JsonProperty("ID")
    private String id;

    @JsonProperty("Signature")
    private Signature signature;

    @JsonProperty("ADR_DATA")
    protected AdrPassCertificateData adrData;

    public String getId() {
        return id;
    }

    public AdrPassCertificate setId(String id) {
        this.id = id;
        return this;
    }

    public AdrPassCertificateData getAdrData() {
        return adrData;
    }

    public Document setAdrData(AdrPassCertificateData adrData) {
        this.adrData = adrData;
        return this;
    }

    public Signature getSignature() {
        return signature;
    }

    public AdrPassCertificate setSignature(Signature signature) {
        this.signature = signature;
        return this;
    }
}
