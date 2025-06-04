package uk.gov.dvsa.enums;

public enum CertificateTypes {
    CVS_PASS("CVS/VTP20"),
    CVS_PASS_WELSH("CVS/VTP20W"),
    CVS_PASS_BILINGUAL("CVS/VTP20_BILINGUAL"),
    CVS_FAIL("CVS/VTP30"),
    CVS_FAIL_WELSH("CVS/VTP30W"),
    CVS_FAIL_BILINGUAL("CVS/VTP30_BILINGUAL"),
    CVS_PSV_PRS("CVS/PSV_PRS"),
    CVS_PSV_PRS_BILINGUAL("CVS/PSV_PRS_BILINGUAL"),

    CVS_HGV_PASS("CVS/VTG5"),
    CVS_HGV_PASS_WELSH("CVS/VTG5W"),
    CVS_HGV_PASS_BILINGUAL("CVS/VTG5_BILINGUAL"),
    CVS_TRL_PASS("CVS/VTG5A"),
    CVS_TRL_PASS_WELSH("CVS/VTG5AW"),
    CVS_TRL_PASS_BILINGUAL("CVS/VTG5A_BILINGUAL"),
    CVS_HGV_TRL_FAIL("CVS/VTG30"),
    CVS_HGV_TRL_FAIL_WELSH("CVS/VTG30W"),
    CVS_HGV_TRL_FAIL_BILINGUAL("CVS/VTG30_BILINGUAL"),
    CVS_HGV_PRS("CVS/HGV_PRS"),
    CVS_HGV_PRS_BILINGUAL("CVS/HGV_PRS_BILINGUAL"),
    CVS_TRL_PRS("CVS/TRL_PRS"),
    CVS_TRL_PRS_BILINGUAL("CVS/TRL_PRS_BILINGUAL"),
    RWT_DATA("CVS/RWT"),
    ADR_PASS("CVS/ADR_PASS"),
    VTG6_VTG7("CVS/VTG6_VTG7"), // plates

    TRAILER_INTO_SERVICE("CVS/TrailerIntoService"),

    IVA30("CVS/IVA30"), // IVA Fail
    MSVA30("CVS/MSVA30"), // MSVA Fail

    VTG12("CVS/VTG12"), // abandoned HGV or Trailer
    VTP12("CVS/VTP12"); // abandoned PSV

    private final String certificateType;

    CertificateTypes(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateType() {
        return certificateType;
    }
}
