package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateDataWelsh;

public class VTG30W extends CvsMotFailCertificate {

    @JsonProperty("FAIL_DATA")
    protected CvsMotFailCertificateDataWelsh failData;

    public CvsMotFailCertificateDataWelsh getFailData() {
        return failData;
    }

    public Document setFailData(CvsMotFailCertificateDataWelsh failData) {
        this.failData = failData;
        return this;
    }
}
