package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class VTP20W extends CvsMotCertificate {

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }

    public String getTestType() {
        return "PSV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTP20W";
    }

    public String getVersionNumberPass() {
        return "1.0";
    }

    public String getRegOrIdHeading() {
        return "Rhif cofrestru";
    }
}
