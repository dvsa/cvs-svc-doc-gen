package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.LecCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.Signature;

public class LecCertificate extends Document {
    @JsonProperty("ID")
    private String id;

    @JsonProperty("Signature")
    private Signature signature;

    @JsonProperty("LEC_DATA")
    protected LecCertificateData lecData;

    public String getId() {
        return id;
    }

    public LecCertificate setId(String id) {
        this.id = id;
        return this;
    }

    public LecCertificateData getLecData() {
        return lecData;
    }

    public Document setLecData(LecCertificateData lecData) {
        this.lecData = lecData;
        return this;
    }

    public Signature getSignature() {
        return signature;
    }

    public LecCertificate setSignature(Signature signature) {
        this.signature = signature;
        return this;
    }
}
