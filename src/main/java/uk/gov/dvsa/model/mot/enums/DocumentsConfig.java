package uk.gov.dvsa.model.mot.enums;

import uk.gov.dvsa.exception.HttpException;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.AdrPassCertificate;
import uk.gov.dvsa.model.cvs.CvsPsvPRS;
import uk.gov.dvsa.model.cvs.LecCertificate;
import uk.gov.dvsa.model.mot.*;

public enum DocumentsConfig {
    EU_CT_20(CertificateTypes.EU_CONTINGENCY_PASS.getType(), CT20.class,
            new String[]{CertificateTemplates.EU_CT20.getCertificateTemplateName()}),
    EU_CT_30(CertificateTypes.EU_CONTINGENCY_FAIL.getType(), CT30.class,
            new String[]{CertificateTemplates.EU_CT30.getCertificateTemplateName()}),

    CT_20(CertificateTypes.CONTINGENCY_PASS.getType(), CT20.class,
            new String[]{CertificateTemplates.CT20.getCertificateTemplateName()}),
    CT_30(CertificateTypes.CONTINGENCY_FAIL.getType(), CT30.class,
            new String[]{CertificateTemplates.CT30.getCertificateTemplateName()}),
    CT_32(CertificateTypes.CONTINGENCY_ADVISORY_NOTICE.getType(), CT32.class,
            new String[]{CertificateTemplates.CT32.getCertificateTemplateName()}),

    VT_30(CertificateTypes.FAIL.getType(), VT30.class,
            new String[]{CertificateTemplates.VT30.getCertificateTemplateName()}),
    VT_20(CertificateTypes.PASS.getType(),VT20.class,
            new String[]{CertificateTemplates.VT20.getCertificateTemplateName()}),
    VT_20W(CertificateTypes.WELSH_PASS.getType(), VT20W.class,
            new String[]{CertificateTemplates.VT20.getCertificateTemplateName(), CertificateTemplates.VT20W.getCertificateTemplateName()}),
    VT_30W(CertificateTypes.WELSH_FAIL.getType(), VT30W.class,
            new String[]{CertificateTemplates.VT30.getCertificateTemplateName(), CertificateTemplates.VT30W.getCertificateTemplateName()}),
    PRS(CertificateTypes.PRS.getType(), PRS.class,
            new String[]{CertificateTemplates.VT20.getCertificateTemplateName(), CertificateTemplates.VT30.getCertificateTemplateName()}),
    PRSW(CertificateTypes.WELSH_PRS.getType(), PRSW.class,
            new String[]{CertificateTemplates.VT20.getCertificateTemplateName(), CertificateTemplates.VT30.getCertificateTemplateName(),
            CertificateTemplates.VT20W.getCertificateTemplateName(), CertificateTemplates.VT30W.getCertificateTemplateName()}),
    VT_32VE(CertificateTypes.ADVISORY_NOTICE.getType(), VT32VE.class,
            new String[]{CertificateTemplates.VT32VE.getCertificateTemplateName()}),
    VT_32VEW(CertificateTypes.WELSH_ADVISORY_NOTICE.getType(), VT32VE.class,
            new String[]{CertificateTemplates.VT32VEW.getCertificateTemplateName()}),
    EU_VT_32VE(CertificateTypes.COMPLIANCE_ADVISORY_NOTICE.getType(), VT32VE.class,
            new String[]{CertificateTemplates.VT32VE.getCertificateTemplateName()}),
    EU_VT_32VEW(CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getType(), VT32VE.class,
            new String[]{CertificateTemplates.VT32VEW.getCertificateTemplateName()}),

    VT29(CertificateTypes.VT29.getType(), VT29.class, new String[]{CertificateTemplates.VT29.getCertificateTemplateName()}),

    CVS_PSV_PRS(CertificateTypes.CVS_PSV_PRS.getType(), CvsPsvPRS.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName(), CertificateTemplates.VTP30.getCertificateTemplateName()}),
    VTP20(CertificateTypes.CVS_PASS.getType(), uk.gov.dvsa.model.cvs.VTP20.class,
            new String[]{CertificateTemplates.VTP20.getCertificateTemplateName()}),
    VTP30(CertificateTypes.CVS_FAIL.getType(), uk.gov.dvsa.model.cvs.VTP30.class,
            new String[]{CertificateTemplates.VTP30.getCertificateTemplateName()}),

    VTG5A(CertificateTypes.CVS_TRL_PASS.getType(), uk.gov.dvsa.model.cvs.VTG5A.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName()}),
    VTG5(CertificateTypes.CVS_HGV_PASS.getType(), uk.gov.dvsa.model.cvs.VTG5.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName()}),
    VTG30(CertificateTypes.CVS_HGV_TRL_FAIL.getType(), uk.gov.dvsa.model.cvs.VTG30.class,
            new String[]{CertificateTemplates.VTG30.getCertificateTemplateName()}),
    ADR_PASS(CertificateTypes.ADR_PASS.getType(), AdrPassCertificate.class,
            new String[]{CertificateTemplates.ADR_PASS.getCertificateTemplateName()}),
    CVS_HGV_PRS(CertificateTypes.CVS_HGV_PRS.getType(), uk.gov.dvsa.model.cvs.CvsHgvPRS.class,
            new String[]{CertificateTemplates.VTG5.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName()}),
    CVS_TRL_PRS(CertificateTypes.CVS_TRL_PRS.getType(), uk.gov.dvsa.model.cvs.CvsTrlPRS.class,
            new String[]{CertificateTemplates.VTG5A.getCertificateTemplateName(), CertificateTemplates.VTG30.getCertificateTemplateName()}),
    LEC_PASS(CertificateTypes.LEC_PASS.getType(), LecCertificate.class,
            new String[]{CertificateTemplates.LEC_PASS.getCertificateTemplateName()}),
    LEC_FAIL(CertificateTypes.LEC_FAIL.getType(), LecCertificate.class,
            new String[]{CertificateTemplates.LEC_FAIL.getCertificateTemplateName()}),

    INSPECTION_CHECKLIST(CertificateTypes.INSPECTION_CHECKLIST.getType(), InspectionChecklist.class,
            new String[]{CertificateTemplates.INSPECTION_CHECKLIST.getCertificateTemplateName()});


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
