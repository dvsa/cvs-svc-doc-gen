package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class CvsPsvPRSBilingual extends VTP30Bilingual {
    public String getPresentedDocumentNamePass() {
        return "VTP20";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTP20W";
    }

    public String getVersionNumberPass() {
        return "1.1";
    }

    public String getVersionNumberPassWelsh() {
        return "1.1";
    }

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }
}
