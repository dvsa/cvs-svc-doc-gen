package htmlverification.framework.page_object;

public enum CertificatePageSelector {
    MAKE_AND_MODEL_ID("make-model"),
    MAKE_ID("make"),
    MODEL_ID("model"),
    DATE_OF_THE_TEST_ID("date-of-test"),
    VIN_ID("vin"),
    REGISTRATION_NUMBER_ID("reg-number"),
    COUNTRY_ID("country"),
    VEHICLE_CATEGORY_ID("vehicle-category"),
    MILEAGE_ID("mileage"),
    MILEAGE_HISTORY_ID("mileage-history"),
    LOCATION_OF_THE_TEST_ID("location-of-test"),
    TESTING_ORG_AND_INSP_NAME_ID("organisation-and-inspection-name"),
    INSPECTION_AUTHORITY("inspection-authority"),
    MOT_TEST_NUMBER_ID("mot-test-number"),
    TEST_STATION_ID("test-station-id"),
    RESULTS_SUMMARY_CLASS("results"),
    DVSA_LOGO_ID("logo"),
    EXPIRY_DATE_ID("expiry-date"),
    ISSUER_INFO_ID("issuer-info"),
    ISSUED_DATE_ID("issued-date"),
    DVSA_LOGO_WELSH_ID("logo-welsh"),
    EARLIEST_DATE_OF_THE_NEXT_TEST("earliest-preserve-date"),
    WATERMARK_SELECTOR("span.running-header__text"),
    DEFECTS_ID("defects"),

    CHASSIS_NUMBER_ID("chassisNumber"),
    APPLICANT_DETAILS_NAME_ID("applicantDetailsName"),
    APPLICANT_DETAILS_STREET_ID("applicantDetailsStreet"),
    VEHICLE_TYPE_ID("vehicleType"),
    FORMATTED_PERMITTED_DANGEROUS_GOODS_ID("formattedPermittedDangerousGoods"),
    WEIGHT_ID("weight"),
    TANK_MANUFACTURER_ID("tankManufacturer"),
    TC2_INIT_APPROVAL_NO_ID("tc2IntermediateApprovalNo"),
    TANK_MANUFACTURE_SERIAL_NO_ID("tankManufactureSerialNo"),
    YEAR_OF_MANUFACTURE_ID("yearOfManufacture"),
    SPECIAL_PROVISIONS_ID("specialProvisions"),
    TANK_CODE_ID("tankCode"),
    STATEMENT_ID("statement"),
    PRODUCT_LIST_ID("productList"),

    SERIAL_NUMBER_ID("serialNumber"),
    TRAP_OR_MOD_ID("trapOrMod"),
    TICKED_MODIFICATION_TYPE("tickedBox"),
    SMOKE_TEST_LIMIT_ID("smokeTestLimit"),
    VRM_ID("vrm"),
    ADDITIONAL_NOTES_ID("additionalNotes"),

    CVS_SIGNATURE_FIRST_PAGE("signature-first-page"),
    CVS_SIGNATURE_SECOND_PAGE("signature-second-page");

    private final String selector;

    CertificatePageSelector(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }
}
