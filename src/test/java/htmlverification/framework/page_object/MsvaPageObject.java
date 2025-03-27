package htmlverification.framework.page_object;

import static htmlverification.framework.page_object.CertificatePageSelector.CVS_SIGNATURE_FIRST_PAGE;
import static htmlverification.framework.page_object.MsvaPageSelector.*;

public class MsvaPageObject extends BasePageObject {
    public MsvaPageObject(String htmlContent) { super(htmlContent); }

    public String getTextByElementId(String id) { return getElementById(id).text(); }
    public String getTextByElementSelector(String selector) { return getElement(selector).text(); }

    public String getSignatureImageSrc() {
        return getElementById(CVS_SIGNATURE_FIRST_PAGE.getSelector()).attr("src");
    }

    public String getSerialNumber() {
        return getElementById(SERIAL_NO.getSelector()).text();
    }

    public String getVehicleZNumber() {
        return getElementById(VEH_Z_NO.getSelector()).text();
    }

    public String getTestType() {
        return getElementById(TYPE.getSelector()).text();
    }

    public String getMakeModel() {
        return getElementById(MAKE_MODEL.getSelector()).text();
    }

    public String getVin() {
        return getElementById(VIN.getSelector()).text();
    }

    public String getDate() {
        return getElementById(DATE.getSelector()).text();
    }

    public String getReapplicationDate() {
        return getElementById(RE_APP_DATE.getSelector()).text();
    }

    public String getTesterName() {
        return getElementById(TESTER_NAME.getSelector()).text();
    }

    public String getStation() {
        return getElementById(STATION.getSelector()).text();
    }

    public String getRequiredStandards() {
        return getElementById(REQUIRED_STANDARDS.getSelector()).text();
    }

    public String getAdditionalDefects() {
        return getElementById(ADDITIONAL_DEFECTS.getSelector()).text();
    }
}
