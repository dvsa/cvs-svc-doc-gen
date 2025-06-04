package uk.gov.dvsa.enums;

import uk.gov.dvsa.exception.HttpException;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.*;

public enum DocumentsConfig {

    CVS_PSV_PRS(CertificateTypes.CVS_PSV_PRS.getCertificateType(), CvsPsvPRS.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName(), CertificateTemplates.VTP30.getCertificateTemplateName()}),
    CVS_PSV_PRS_BILINGUAL(CertificateTypes.CVS_PSV_PRS_BILINGUAL.getCertificateType(), CvsPsvPRSBilingual.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName(), CertificateTemplates.VTP30.getCertificateTemplateName(),
                    CertificateTemplates.VTP20W.getCertificateTemplateName(), CertificateTemplates.VTP30W.getCertificateTemplateName()}),
    VTP20(CertificateTypes.CVS_PASS.getCertificateType(), uk.gov.dvsa.model.cvs.VTP20.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName()}),
    VTP20W(CertificateTypes.CVS_PASS_WELSH.getCertificateType(), uk.gov.dvsa.model.cvs.VTP20W.class,
            new String[]{CertificateTemplates.VTP20W.getCertificateTemplateName()}),
    VTP30(CertificateTypes.CVS_FAIL.getCertificateType(), uk.gov.dvsa.model.cvs.VTP30.class,
            new String[]{CertificateTemplates.VTP30.getCertificateTemplateName()}),
    VTP30W(CertificateTypes.CVS_FAIL_WELSH.getCertificateType(), uk.gov.dvsa.model.cvs.VTP30W.class,
            new String[]{CertificateTemplates.VTP30W.getCertificateTemplateName()}),
    CVS_FAIL_BILINGUAL(CertificateTypes.CVS_FAIL_BILINGUAL.getCertificateType(), VTP30Bilingual.class,
            new String[]{CertificateTemplates.VTP30.getCertificateTemplateName(), CertificateTemplates.VTP30W.getCertificateTemplateName()}),
    VTG5A(CertificateTypes.CVS_TRL_PASS.getCertificateType(), uk.gov.dvsa.model.cvs.VTG5A.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName()}),
    VTG5AW(CertificateTypes.CVS_TRL_PASS_WELSH.getCertificateType(), uk.gov.dvsa.model.cvs.VTG5AW.class,
            new String[]{CertificateTemplates.VTG5AW.getCertificateTemplateName()}),
    VTG5(CertificateTypes.CVS_HGV_PASS.getCertificateType(), uk.gov.dvsa.model.cvs.VTG5.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName()}),
    VTG5W(CertificateTypes.CVS_HGV_PASS_WELSH.getCertificateType(), uk.gov.dvsa.model.cvs.VTG5W.class,
            new String[]{CertificateTemplates.VTG5W.getCertificateTemplateName()}),
    VTG30(CertificateTypes.CVS_HGV_TRL_FAIL.getCertificateType(), uk.gov.dvsa.model.cvs.VTG30.class,
            new String[]{CertificateTemplates.VTG30.getCertificateTemplateName()}),

    VTG30W(CertificateTypes.CVS_HGV_TRL_FAIL_WELSH.getCertificateType(), uk.gov.dvsa.model.cvs.VTG30W.class,
            new String[]{CertificateTemplates.VTG30W.getCertificateTemplateName()}),
    ADR_PASS(CertificateTypes.ADR_PASS.getCertificateType(), AdrPassCertificate.class,
            new String[]{CertificateTemplates.ADR_PASS.getCertificateTemplateName()}),

    VTG6_VTG7(CertificateTypes.VTG6_VTG7.getCertificateType(), MinistryPlate.class,
            new String[]{CertificateTemplates.VTG6_VTG7.getCertificateTemplateName()}),

    TRAILER_INTO_SERVICE(CertificateTypes.TRAILER_INTO_SERVICE.getCertificateType(), TrailerIntoService.class,
            new String[]{CertificateTemplates.TRAILER_INTO_SERVICE.getCertificateTemplateName()}),

    RWT_DATA(CertificateTypes.RWT_DATA.getCertificateType(), RwtCertificate.class,
            new String[]{CertificateTemplates.RWT_DATA.getCertificateTemplateName()}),
    CVS_HGV_PRS(CertificateTypes.CVS_HGV_PRS.getCertificateType(), uk.gov.dvsa.model.cvs.CvsHgvPRS.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName()}),
    CVS_HGV_PRS_BILINGUAL(CertificateTypes.CVS_HGV_PRS_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsHgvPRSBilingual.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName(),
            CertificateTemplates.VTG5W.getCertificateTemplateName(), CertificateTemplates.VTG30W.getCertificateTemplateName()}),
    CVS_PASS_BILINGUAL(CertificateTypes.CVS_PASS_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsPsvPassBilingual.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName(), CertificateTemplates.VTP20W.getCertificateTemplateName()}),
    CVS_HGV_PASS_BILINGUAL(CertificateTypes.CVS_HGV_PASS_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsHgvPassBilingual.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName(), CertificateTemplates.VTG5W.getCertificateTemplateName()}),
    CVS_TRL_PASS_BILINGUAL(CertificateTypes.CVS_TRL_PASS_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsTrlPassBilingual.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName(), CertificateTemplates.VTG5AW.getCertificateTemplateName()}),
    CVS_HGV_TRL_FAIL_BILINGUAL(CertificateTypes.CVS_HGV_TRL_FAIL_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsHgvTrlFailBilingual.class,
            new String[]{CertificateTemplates.VTG30.getCertificateTemplateName(), CertificateTemplates.VTG30W.getCertificateTemplateName()}),
    CVS_TRL_PRS(CertificateTypes.CVS_TRL_PRS.getCertificateType(), uk.gov.dvsa.model.cvs.CvsTrlPRS.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName()}),

    CVS_TRL_PRS_BILINGUAL(CertificateTypes.CVS_TRL_PRS_BILINGUAL.getCertificateType(), uk.gov.dvsa.model.cvs.CvsTrlPRSBilingual.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName(),
                    CertificateTemplates.VTG5AW.getCertificateTemplateName(), CertificateTemplates.VTG30W.getCertificateTemplateName()}),

    IVA30(CertificateTypes.IVA30.getCertificateType(), IVA30.class,
            new String[]{CertificateTemplates.IVA30.getCertificateTemplateName()}),

    MSVA30(CertificateTypes.MSVA30.getCertificateType(), MSVA30.class,
            new String[]{CertificateTemplates.MSVA30.getCertificateTemplateName()}),

    VTG12(CertificateTypes.VTG12.getCertificateType(), VTG12.class,
            new String[]{CertificateTemplates.VTG12.getCertificateTemplateName()}),

    VTP12(CertificateTypes.VTP12.getCertificateType(), VTP12.class,
            new String[]{CertificateTemplates.VTP12.getCertificateTemplateName()});

    private final String documentName;
    private final Class<? extends Document> baseClass;
    private final String[] templateNames; // names and order of handlebars templates used to generate a PDF

    DocumentsConfig(String documentName, Class<? extends Document> baseClass, String[] templateNames) {
        this.documentName = documentName;
        this.baseClass = baseClass;
        this.templateNames = templateNames;
    }

    public Class<? extends Document> getBaseClass() {
        return baseClass;
    }

    public static DocumentsConfig fromDocumentName(String name) {
        for (DocumentsConfig document: DocumentsConfig.values()) {
            if (document.getDocumentName().equals(name)) {
                return document;
            }
        }
        throw new HttpException.NotFoundException("Unknown document name: " + name);
    }

    public String getDocumentName() {
        return documentName;
    }

    public String[] getTemplateNames() {
        return templateNames;
    }
}
