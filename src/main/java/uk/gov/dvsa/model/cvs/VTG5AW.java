package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class VTG5AW extends CvsMotCertificate {

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "Trailer";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5AW";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getRegOrIdHeadingWelsh() { return "Rhif adnabod";  }
}
