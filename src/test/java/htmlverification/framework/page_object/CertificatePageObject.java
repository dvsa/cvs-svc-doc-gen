package htmlverification.framework.page_object;

import htmlverification.framework.component.DefectSummaryComponent;
import htmlverification.framework.component.MileageHistoryComponent;
import org.jsoup.nodes.Element;

import static htmlverification.framework.page_object.CertificatePageSelector.*;

public class CertificatePageObject extends BasePageObject {

    public CertificatePageObject(String htmlContent) {
        super(htmlContent);
    }

    public String getTextByElementId(String id) { return getElementById(id).text(); }
    public String getTextByElementSelector(String selector) { return getElement(selector).text(); }


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

    public String getRecallsHeader() {return getElementById(RECALLS_HEADER.getSelector()).text();}

    public String getRecallsBody() {return getElementById(RECALLS_BODY.getSelector()).text();}

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
        return getElementById(CHASSISNUMBER_ID.getSelector()).text();
    }

    public String getApplicantDetailsName() {
        return getElementById(APPLICANT_DETAILS_NAME_ID.getSelector()).text();
    }

    public String getApplicantDetailsStreet() { return getElementById(APPLICANT_DETAILS_STREET_ID.getSelector()).text(); }

    public String getVehicleType() { return getElementById(VEHICLE_TYPE_ID.getSelector()).text(); }
    public String getAdrVehicleType() { return getElementById(ADR_VEHICLE_TYPE_ID.getSelector()).text(); }


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

    public String getNotes() { return getElementById(NOTES_ID.getSelector()).text(); }

    public String getTestTypeDate() { return getElementById(TEST_TYPE_DATE_ID.getSelector()).text(); }

    public String getAtfNameAtfPNumber() { return getElementById(ATF_NAME_ATF_P_NUMBER_ID.getSelector()).text(); }

    public String getCertTitle() {return getElementById(CERT_TITLE.getSelector()).text();}
    public String getDgvw() {return getElementById(DGVW.getSelector()).text();}
    public String getWeight2() {return getElementById(WEIGHT2.getSelector()).text();}
    public String getVehicleNumber() {return getElementById(VEHICLE_NUMBER.getSelector()).text();}
    public String getIssuersName() {return getElementById(ISSUERS_NAME.getSelector()).text();}
    public String getDateOfInspection() {return getElementById(DATE_OF_INSPECTION.getSelector()).text();}
    public String getTestStationPNumber() {return getElementById(TEST_STATION_PNUMBER.getSelector()).text();}
    public String getDocumentNumber() {return getElementById(DOCUMENT_NUMBER.getSelector()).text();}
    public String getRwtDate() {return getElementById(RWT_DATE.getSelector()).text();}
    public String getRwtDocNumber() {return getElementById(RWT_DOC_NUMBER.getSelector()).text();}
    public String getRwtDocVersion() {return getElementById(RWT_DOC_VERSION.getSelector()).text();}
    public String getRwtDefect(int id){
        switch (id){
            case 0:
                return getElementById(RWT_DEFECTS_FIRST.getSelector()).text();
            case 1:
                 return getElementById(RWT_DEFECTS_SECOND.getSelector()).text();
            case 2:
                return getElementById(RWT_DEFECTS_THIRD.getSelector()).text();
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
    public String getTrn() { return getElementById(TRN.getSelector()).text();}

    public String getPlateSerialNumber() {
        return getElementById(PLATE_SERIAL_NUMBER.getSelector()).text();
    }

    public String getDtpNumber() {
        return getElementById(DTP_NUM.getSelector()).text();
    }

    public String getPrimaryVrm() {
        return getElementById(PRIMARY_VRM.getSelector()).text();
    }

    public String getVariantNumber() {
        return getElementById(VARIANT_NUM.getSelector()).text();
    }

    public String getApprovalTypeNumber() {
        return getElementById(APPROVAL_TYPE_NUM.getSelector()).text();
    }

    public String getSpeedLimiterMrk() {
        return getElementById(SPEED_LIMITER_MRK.getSelector()).text();
    }

    public String getFunctionCode() {
        return getElementById(FUNCTION_CODE.getSelector()).text();
    }

    public String getRegnDate() {
        return getElementById(REGN_DATE.getSelector()).text();
    }

    public String getManufactureYear() {
        return getElementById(MANUFACTURE_YEAR.getSelector()).text();
    }

    public String getGrossGbWeight() {
        return getElementById(GROSS_GB_WEIGHT.getSelector()).text();
    }

    public String getGrossEecWeight() {
        return getElementById(GROSS_EEC_WEIGHT.getSelector()).text();
    }

    public String getGrossDesignWeight() {
        return getElementById(GROSS_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getTrainGbWeight() {
        return getElementById(TRAIN_GB_WEIGHT.getSelector()).text();
    }

    public String getTrainEecWeight() {
        return getElementById(TRAIN_EEC_WEIGHT.getSelector()).text();
    }

    public String getTrainDesignWeight() {
        return getElementById(TRAIN_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getMaxTrainGbWeight() {
        return getElementById(MAX_TRAIN_GB_WEIGHT.getSelector()).text();
    }

    public String getMaxTrainEecWeight() {
        return getElementById(MAX_TRAIN_EEC_WEIGHT.getSelector()).text();
    }

    public String getMaxLoadOnCoupling() {
        return getElementById(MAX_LOAD_ON_COUPLING.getSelector()).text();
    }

    public String getDimensionLength() {
        return getElementById(DIMENSION_LENGTH.getSelector()).text();
    }

    public String getDimensionWidth() {
        return getElementById(DIMENSION_WIDTH.getSelector()).text();
    }

    public String getFrontVehicleTo5thWheelCouplingMin() {
        return getElementById(FRONT_AXLE_TO_5TH_COUPLING_MIN.getSelector()).text();
    }

    public String getFrontVehicleTo5thWheelCouplingMax() {
        return getElementById(FRONT_AXLE_TO_5TH_COUPLING_MAX.getSelector()).text();
    }

    public String getCouplingCenterToRearTrlMax() {
        return getElementById(COUPLING_CENTER_TO_REAR_TRL_MAX.getSelector()).text();
    }

    public String getCouplingCenterToRearTrlMin() {
        return getElementById(COUPLING_CENTER_TO_REAR_TRL_MIN.getSelector()).text();
    }

    public String getPlateIssueDate() {
        return getElementById(PLATE_ISSUE_DATE.getSelector()).text();
    }

    public String getTyreUseCode() {
        return getElementById(TYRE_USE_CODE.getSelector()).text();
    }

    public String getAxle1GbWeight() {
        return getElementById(AXLE1_GB_WEIGHT.getSelector()).text();
    }

    public String getAxle2GbWeight() {
        return getElementById(AXLE2_GB_WEIGHT.getSelector()).text();
    }

    public String getAxle3GbWeight() {
        return getElementById(AXLE2_GB_WEIGHT.getSelector()).text();
    }

    public String getAxle4GbWeight() {
        return getElementById(AXLE2_GB_WEIGHT.getSelector()).text();
    }

    public String getAxle1EecWeight() {
        return getElementById(AXLE1_EEC_WEIGHT.getSelector()).text();
    }

    public String getAxle2EecWeight() {
        return getElementById(AXLE2_EEC_WEIGHT.getSelector()).text();
    }

    public String getAxle3EecWeight() {
        return getElementById(AXLE2_EEC_WEIGHT.getSelector()).text();
    }

    public String getAxle4EecWeight() {
        return getElementById(AXLE2_EEC_WEIGHT.getSelector()).text();
    }

    public String getAxle1DesignWeight() {
        return getElementById(AXLE1_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getAxle2DesignWeight() {
        return getElementById(AXLE2_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getAxle3DesignWeight() {
        return getElementById(AXLE3_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getAxle4DesignWeight() {
        return getElementById(AXLE4_DESIGN_WEIGHT.getSelector()).text();
    }

    public String getAxle1TyreSize() {
        return getElementById(AXLE1_TYRE_SIZE.getSelector()).text();
    }

    public String getAxle2TyreSize() {
        return getElementById(AXLE2_TYRE_SIZE.getSelector()).text();
    }

    public String getAxle3TyreSize() {
        return getElementById(AXLE3_TYRE_SIZE.getSelector()).text();
    }

    public String getAxle4TyreSize() {
        return getElementById(AXLE4_TYRE_SIZE.getSelector()).text();
    }

    public String getAxle1PlyRating() {
        return getElementById(AXLE1_PLY_RATING.getSelector()).text();
    }

    public String getAxle2PlyRating() {
        return getElementById(AXLE2_PLY_RATING.getSelector()).text();
    }

    public String getAxle3PlyRating() {
        return getElementById(AXLE3_PLY_RATING.getSelector()).text();
    }

    public String getAxle4PlyRating() {
        return getElementById(AXLE4_PLY_RATING.getSelector()).text();
    }

    public String getAxle1FitmentCode() {
        return getElementById(AXLE1_FITMENT_CODE.getSelector()).text();
    }

    public String getAxle2FitmentCode() {
        return getElementById(AXLE2_FITMENT_CODE.getSelector()).text();
    }

    public String getAxle3FitmentCode() {
        return getElementById(AXLE3_FITMENT_CODE.getSelector()).text();
    }

    public String getAxle4FitmentCode() { return getElementById(AXLE4_FITMENT_CODE.getSelector()).text(); }

    public String getDocumentType() {
        return getElementById(DOCUMENT_TYPE.getSelector()).text();
    }

    public String getRegulationText() {
        return getElementById(REGULATION_TEXT.getSelector()).text();
    }

    public String getSpacedRegistrationNumber(Integer index) {
        return getElementById(REGISTRATION_TEXT.getSelector()+index).text();
    }

    public String getFirstPageTitle() {
        return getElementById(FIRST_PAGE_TITLE.getSelector()).text();
    }

    public String getFooterDocumentType() {
        return getElementById(FOOTER_DOCUMENT_TYPE.getSelector()).text();
    }

    public String getFooterTestNumber() {
        return getElementById(FOOTER_TEST_NUMBER.getSelector()).text();
    }

    public String getFooterDatePopulated() {
        return getElementById(FOOTER_DATE_POPULATED.getSelector()).text();
    }

    public String getRunningHeaderLeft() {
        return getElement("." + RUNNING_HEADER_LEFT.getSelector()).text();
    }

    public String getRunningHeaderRight() {
        return getElement("." + RUNNING_HEADER.getSelector()).getElementsByTag("span").text();
    }

    public String getVehicleTestType() {
        return getElementById(VEHICLE_TYPE_TEXT.getSelector()).text();
    }

    public String getSanctionText() {
        return getElementById(SECTION_TEXT.getSelector()).text();
    }

    public String getReasonsForAbandonment(Integer index) {
        return getElementById(REASONS_FOR_ABANDONMENT.getSelector()+index).text();
    }

    public String getAdditionalComments() {
        return getElementById(ADDITIONAL_COMMENTS.getSelector()).text();
    }

    public String getDataProtectionWithDocumentType() {
        return getElementById(DATA_PROTECTION_WITH_DOCUMENT_TYPE.getSelector()).text();
    }

    public String getSignature() {
        return getElementById(SIGNATURE.getSelector()).attr("src");
    }

    public String getPrintName() {
        return getElementById(PRINT_NAME.getSelector()).text();
    }

    public String getLocation() {
        return getElementById(LOCATION.getSelector()).text();
    }

    public String getLocationNumber() {
        return getElementById(LOCATION_NUMBER.getSelector()).text();
    }

    public String getDateOfTest() {
        return getElementById(DATE_OF_THE_TEST.getSelector()).text();
    }
}