package uk.gov.dvsa.enums;

public enum CertificateTemplates {

    VTP20("CommercialVehicles/pass"),
    VTP20W("CommercialVehicles/passWelsh"),
    VTP30("CommercialVehicles/fail"),
    VTP30W("CommercialVehicles/failWelsh"),

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

    IVA30("CommercialVehicles/IVA30"),

    MSVA30("CommercialVehicles/MSVA30"),

    VTG12("CommercialVehicles/Abandoned"),

    VTP12("CommercialVehicles/Abandoned");

    private final String certificateTemplateName;

    CertificateTemplates(String certificateTemplateName) {
        this.certificateTemplateName = certificateTemplateName;
    }

    public String getCertificateTemplateName() {
        return certificateTemplateName;
    }
}
