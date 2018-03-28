package uk.gov.dvsa.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uk.gov.dvsa.model.mot.enums.CertificateTypes;

import com.github.jknack.handlebars.Handlebars;

public class HtmlGeneratorFactory {
    private List<String> VT32documentNames = Collections.unmodifiableList(Arrays.asList(
        CertificateTypes.ADVISORY_NOTICE.getType(),
        CertificateTypes.WELSH_ADVISORY_NOTICE.getType(),
        CertificateTypes.COMPLIANCE_ADVISORY_NOTICE.getType(),
        CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getType()));

    public HtmlGenerator create(String documentName) {
        Handlebars handlebars = new Handlebars();
        return isDocumentVT32VE(documentName) ? new VT32VEHtmlGenerator(handlebars) : new HtmlGenerator(handlebars);
    }

    private boolean isDocumentVT32VE(String documentName) {
        return VT32documentNames.contains(documentName);
    }
}
