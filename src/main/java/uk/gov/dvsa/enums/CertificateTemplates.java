package uk.gov.dvsa.enums;

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
    VTP20W("CommercialVehicles/passWelsh"),
    VTP30("CommercialVehicles/fail"),

    VTG5("CommercialVehicles/passNoSeatbeltFields"),
    VTG5W("CommercialVehicles/passNoSeatbeltFieldsWelsh"),
    VTG5A("CommercialVehicles/passNoSeatbeltFields"),
    VTG5AW("CommercialVehicles/passNoSeatbeltFieldsWelsh"),
    VTG30("CommercialVehicles/VTG30"),
    VTG30W("CommercialVehicles/VTG30Welsh"),
    ADR_PASS("CommercialVehicles/ADR_PASS"),

    VTG6_VTG7("CommercialVehicles/VTG6_VTG7"),

    TRAILER_INTO_SERVICE("CommercialVehicles/TrailerIntoService"),

    RWT_DATA("CommercialVehicles/RWT_DATA"),

    INSPECTION_CHECKLIST("MOT/Inspection_Checklist"),

    IVA30("CommercialVehicles/IVA30"),

    MSVA30("CommercialVehicles/MSVA30");

    private final String certificateTemplateName;

    CertificateTemplates(String certificateTemplateName) {
        this.certificateTemplateName = certificateTemplateName;
    }

    public String getCertificateTemplateName() {
        return certificateTemplateName;
    }
}
