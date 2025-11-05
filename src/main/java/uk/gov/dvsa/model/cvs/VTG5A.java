package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData;

public class VTG5A extends CvsMotCertificate {

    public Document setData(CvsMotCertificateData data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "Trailer";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5A";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Identification number";  }
}
