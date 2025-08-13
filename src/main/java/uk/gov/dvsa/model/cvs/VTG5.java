package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData;

public class VTG5 extends CvsMotCertificate {

    public Document setData(CvsMotCertificateData data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getRegOrIdHeading() { return "Registration number";  }
}
