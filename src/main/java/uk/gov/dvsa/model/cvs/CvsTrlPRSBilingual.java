package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class CvsTrlPRSBilingual extends CvsHgvTrlFailBilingual {
    public String getTestType() {
        return "TRL";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5A";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTG5AW";
    }

    public String getVersionNumberPass() {
        return "1.0";
    }

    public String getVersionNumberPassWelsh() {
        return "1.0";
    }

    public Document setData(CvsMotCertificateDataWelsh data) {
        this.data = data;
        return this;
    }
}
