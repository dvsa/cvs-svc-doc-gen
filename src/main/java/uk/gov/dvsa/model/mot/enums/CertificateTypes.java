package uk.gov.dvsa.model.mot.enums;

public enum CertificateTypes {

    PASS("MOT/VT20"),
    FAIL("MOT/VT30"),
    WELSH_PASS("MOT/VT20W"),
    WELSH_FAIL("MOT/VT30W"),
    PRS("MOT/PRS"),
    WELSH_PRS("MOT/PRSW"),

    EU_CONTINGENCY_PASS("MOT/EU_CT20"),
    EU_CONTINGENCY_FAIL("MOT/EU_CT30"),

    CONTINGENCY_PASS("MOT/CT20"),
    CONTINGENCY_FAIL("MOT/CT30"),
    CONTINGENCY_ADVISORY_NOTICE("MOT/CT32"),

    ADVISORY_NOTICE("MOT/VT32VE"),
    WELSH_ADVISORY_NOTICE("MOT/VT32VEW"),
    COMPLIANCE_ADVISORY_NOTICE("MOT/EU_VT32VE"),
    COMPLIANCE_WELSH_ADVISORY_NOTICE("MOT/EU_VT32VEW"),

    VT29("MOT/VT29"),


    CVS_PASS("CVS/VTP20"),
    CVS_FAIL("CVS/VTP30"),
    CVS_PSV_PRS("CVS/PSV_PRS"),

    CVS_HGV_PASS("CVS/VTG5"),
    CVS_TRL_PASS("CVS/VTG5A"),
    CVS_HGV_TRL_FAIL("CVS/VTG30"),
    CVS_HGV_PRS("CVS/HGV_PRS"),
    CVS_TRL_PRS("CVS/TRL_PRS"),
    RWT_DATA("CVS/RWT"),
    ADR_PASS("CVS/ADR_PASS"),
    VTG6_VTG7("CVS/VTG6_VTG7"),

    INSPECTION_CHECKLIST("MOT/InspectionChecklist");

    private final String type;

    CertificateTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
