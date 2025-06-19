package uk.gov.dvsa.model.cvs;

import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateDataWelsh;

public class CvsHgvPRSBilingual extends CvsHgvTrlFailBilingual {

    public String getTestType() {
        return "HGV";
    }

    public String getPresentedDocumentNamePass() {
        return "VTG5";
    }

    public String getPresentedDocumentNamePassWelsh() {
        return "VTG5W";
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