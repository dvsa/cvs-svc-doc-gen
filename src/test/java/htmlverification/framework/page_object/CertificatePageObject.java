package htmlverification.framework.page_object;

import htmlverification.framework.component.DefectSummaryComponent;
import htmlverification.framework.component.MileageHistoryComponent;
import org.jsoup.nodes.Element;

import static htmlverification.framework.page_object.CertificatePageSelector.*;

public class CertificatePageObject extends BasePageObject {

    public CertificatePageObject(String htmlContent) {
        super(htmlContent);
    }

    public MileageHistoryComponent getMileageHistoryComponent() {
        Element mileageHistoryElement = getElementById(CertificatePageSelector.MILEAGE_HISTORY_ID.getSelector());

        return new MileageHistoryComponent(mileageHistoryElement);
    }

    public DefectSummaryComponent getDefectSummaryComponent() {
        Element defectSummaryElement = getElement("." + RESULTS_SUMMARY_CLASS.getSelector());

        return new DefectSummaryComponent(defectSummaryElement);
    }

    public String getVin() {
        return getElementById(VIN_ID.getSelector()).text();
    }

    public String getCountryOfRegistration() {
        return getElementById(COUNTRY_ID.getSelector()).text();
    }

    public String getMakeAndModel() {
        return getElementById(MAKE_AND_MODEL_ID.getSelector()).text();
    }

    public String getVehicleCategory() {
        return getElementById(VEHICLE_CATEGORY_ID.getSelector()).text();
    }

    public String getMileage() {
        return getElementById(MILEAGE_ID.getSelector()).text();
    }

    public String getDateOfTheTest() {
        return getElementById(DATE_OF_THE_TEST_ID.getSelector()).text();
    }

    public String getExpiryDate() {
        return getElementById(EXPIRY_DATE_ID.getSelector()).text();
    }

    public String getLocationOfTheTest() {
        return getElementById(LOCATION_OF_THE_TEST_ID.getSelector()).text();
    }

    public String getInspectionAuthority() {
        return getElementById(INSPECTION_AUTHORITY.getSelector()).text();
    }

    public String getTestingOrganisationAndInspectorsName() {
        return getElementById(TESTING_ORG_AND_INSP_NAME_ID.getSelector()).text();
    }

    public String getTestStationId() {
        return getElementById(TEST_STATION_ID.getSelector()).text();
    }

    public String getTestNumber() {
        return getElementById(MOT_TEST_NUMBER_ID.getSelector()).text();
    }

    public String getIssuerInfo() {
        return getElementById(ISSUER_INFO_ID.getSelector()).text();
    }

    public String getEarliestDateOfTheNextTest() {
        return getElementById(EARLIEST_DATE_OF_THE_NEXT_TEST.getSelector()).text();
    }

    public Element getWatermark() {
        return getElement(WATERMARK_SELECTOR.getSelector());
    }

    public String getRegistrationNumber() {
        return getElementById(REGISTRATION_NUMBER_ID.getSelector()).text();
    }

    public String getMake() {
        return getElementById(MAKE_ID.getSelector()).text();
    }

    public String getModel() {
        return getElementById(MODEL_ID.getSelector()).text();
    }

    public String getIssuedDate() {
        return getElementById(ISSUED_DATE_ID.getSelector()).text();
    }

    public Element getLogo() {
        return getElementById(DVSA_LOGO_ID.getSelector());
    }

    public Element getWelshLogo() {
        return getElementById(DVSA_LOGO_WELSH_ID.getSelector());
    }

    public String getDefects() {
        return getElementById(DEFECTS_ID.getSelector()).text();
    }

    public String getSignatureImageSrc() {
        return getElementById(CVS_SIGNATURE_FIRST_PAGE.getSelector()).attr("src");
    }

    public String getChassisNumber() {
        return getElementById(CHASSIS_NUMBER_ID.getSelector()).text();
    }

    public String getApplicantDetailsName() {
        return getElementById(APPLICANT_DETAILS_NAME_ID.getSelector()).text();
    }

    public String getApplicantDetailsStreet() { return getElementById(APPLICANT_DETAILS_STREET_ID.getSelector()).text(); }

    public String getVehicleType() { return getElementById(VEHICLE_TYPE_ID.getSelector()).text(); }

    public String getFormattedPermittedDangerousGoods() { return getElementById(FORMATTED_PERMITTED_DANGEROUS_GOODS_ID.getSelector()).text(); }

    public String getWeight() { return getElementById(WEIGHT_ID.getSelector()).text(); }

    public String getTankManufacturer() { return getElementById(TANK_MANUFACTURER_ID.getSelector()).text(); }

    public String getTc2InitApprovalNo() { return getElementById(TC2_INIT_APPROVAL_NO_ID.getSelector()).text(); }

    public String getTankManufactureSerialNo() { return getElementById(TANK_MANUFACTURE_SERIAL_NO_ID.getSelector()).text(); }

    public String getYearOfManufacture() { return getElementById(YEAR_OF_MANUFACTURE_ID.getSelector()).text(); }

    public String getSpecialProvisions() { return getElementById(SPECIAL_PROVISIONS_ID.getSelector()).text(); }

    public String getTankCode() { return getElementById(TANK_CODE_ID.getSelector()).text(); }

    public String getStatement() { return getElementById(STATEMENT_ID.getSelector()).text(); }

    public String getProductList() { return getElementById(PRODUCT_LIST_ID.getSelector()).text(); }

    public String getSerialNumber() {return getElementById(SERIAL_NUMBER_ID.getSelector()).text(); }

    public String getVrm() {return getElementById(VRM_ID.getSelector()).text(); }

    public String getTrapOrMod() {return getElementById(TRAP_OR_MOD_ID.getSelector()).text(); }

    public String getTickedModificationType() {return getElementById(TICKED_MODIFICATION_TYPE.getSelector()).text(); }

    public String getSmokeTestLimit() {return getElementById(SMOKE_TEST_LIMIT_ID.getSelector()).text(); }

    public String getAdditionalNotes() {return getElementById(ADDITIONAL_NOTES_ID.getSelector()).text(); }
}
