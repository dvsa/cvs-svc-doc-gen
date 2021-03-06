package uk.gov.dvsa.model.mot;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.mot.certificateData.MotCertificateData;

public abstract class MotCertificate extends Document {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("IssuerInfo")
    private String issuerInfo;

    @JsonProperty("DATA")
    protected MotCertificateData data;

    @JsonProperty("CertificateIssuerInfo")
    private String certificateIssuerInfo;

    @JsonProperty("CertificateIssuerInfoCy")
    private String certificateIssuerInfoCy;

    @JsonProperty(value = "IsIssuerSignatureVisible", defaultValue = "true")
    private Boolean isIssuerSignatureVisible = true;

    public String getId() {
        return id;
    }

    public MotCertificate setId(String id) {
        this.id = id;
        return this;
    }

    public String getIssuerInfo() {
        return issuerInfo;
    }

    public MotCertificate setIssuerInfo(String issuerInfo) {
        this.issuerInfo = issuerInfo;
        return this;
    }

    public MotCertificateData getData() {
        return data;
    }

    public Document setData(MotCertificateData data) {
        this.data = data;
        return this;
    }

    public String getCertificateIssuerInfo() {
        return certificateIssuerInfo;
    }

    public MotCertificate setCertificateIssuerInfo(String certificateIssuerInfo) {
        this.certificateIssuerInfo = certificateIssuerInfo;
        return this;
    }

    public String getCertificateIssuerInfoCy() {
        return certificateIssuerInfoCy;
    }

    public MotCertificate setCertificateIssuerInfoCy(String certificateIssuerInfoCy) {
        this.certificateIssuerInfoCy = certificateIssuerInfoCy;
        return this;
    }

    public Boolean isIssuerSignatureVisible() {
        return this.isIssuerSignatureVisible;
    }

    public MotCertificate setIssuerSignatureVisible(Boolean issuerVisible) {
        this.isIssuerSignatureVisible = issuerVisible;
        return this;
    }
}
