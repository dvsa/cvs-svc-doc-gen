package htmlverification.framework.page_object;

public enum CertificatePageSelector {
    MAKE_AND_MODEL_ID("make-model"),
    DATE_OF_THE_TEST_ID("date-of-test"),
    VIN_ID("vin"),
    REGISTRATION_NUMBER_ID("reg-number"),
    COUNTRY_ID("country"),
    VEHICLE_CATEGORY_ID("vehicle-category"),
    MILEAGE_ID("mileage"),
    MILEAGE_HISTORY_ID("mileage-history"),
    LOCATION_OF_THE_TEST_ID("location-of-test"),
    TESTING_ORG_AND_INSP_NAME_ID("organisation-and-inspection-name"),
    MOT_TEST_NUMBER_ID("mot-test-number"),
    RESULTS_SUMMARY_CLASS("results"),
    DVSA_LOGO_SELECTOR("img[src*=dvsa_crest.png]"),
    EXPIRY_DATE_ID("expiry-date");

    private final String selector;

    CertificatePageSelector(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }
}
