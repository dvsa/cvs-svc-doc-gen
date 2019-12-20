package uk.gov.dvsa.model.mot.enums;

public enum CertificateTemplates {

    EU_CT20("MOT/EU_CT20"),
    EU_CT30("MOT/EU_CT30"),

    CT20("MOT/CT20"),
    CT30("MOT/CT30"),
    CT32("MOT/CT32"),

    VT30("MOT/VT30"),
    VT20("MOT/VT20"),
    VT20W("MOT/VT20W"),
    VT30W("MOT/VT30W"),

    VT32VEW("MOT/VT32VEW"),
    VT32VE("MOT/VT32VE"),

    VT29("MOT/VT29"),

    VTP20("CommercialVehicles/pass"),
    VTP30("CommercialVehicles/fail"),

    VTG5("CommercialVehicles/passNoSeatbeltFields"),
    VTG5A("CommercialVehicles/passNoSeatbeltFields"),
    VTG30("CommercialVehicles/VTG30"),
    ADR_PASS("CommercialVehicles/ADR_PASS"),
    LEC_PASS("CommercialVehicles/LEC_PASS"),
    LEC_FAIL("CommercialVehicles/LEC_FAIL"),

    INSPECTION_CHECKLIST("MOT/Inspection_Checklist");

    private final String certificateTemplateName;

    CertificateTemplates(String certificateTemplateName) {
        this.certificateTemplateName = certificateTemplateName;
    }

    public String getCertificateTemplateName() {
        return certificateTemplateName;
    }
}
