package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

public abstract class CvsMotCertificate extends Document {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Signature")
    private Signature signature;

    @JsonProperty("DATA")
    protected CvsMotCertificateData data;

    public String getId() {
        return id;
    }

    public CvsMotCertificate setId(String id) {
        this.id = id;
        return this;
    }

    public CvsMotCertificateData getData() {
        return data;
    }

    public Document setData(CvsMotCertificateData data) {
        this.data = data;
        return this;
    }

    public Signature getSignature() {
        return signature;
    }

    public CvsMotCertificate setSignature(Signature signature) {
        this.signature = signature;
        return this;
    }
}
