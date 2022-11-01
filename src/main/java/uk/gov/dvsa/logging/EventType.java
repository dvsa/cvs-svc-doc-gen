package uk.gov.dvsa.logging;

public enum EventType {
    CERT_LAMBDA_START("CERT-LAMBDA-START", "Loading Java Lambda handler of PdfGenerator"),
    CERT_REQUEST_RECEIVED("CERT-REQUEST-RECEIVED", "Document generation start"),
    CERT_REQUEST_PARSING("CERT-REQUEST-PARSING", "Parsing request"),
    CERT_TEMPLATES_COMPILATION("CERT-TEMPLATES-COMPILATION", "Compiling templates"),
    CERT_HTML_GENERATION("CERT-HTML-GENERATION", "Generating HTML"),
    CERT_PDF_GENERATION("CERT-PDF-GENERATION", "Generating PDF"),
    CERT_PROCESSED_SUCCESSFULLY("CERT-PROCESSED-SUCCESSFULLY", "Certificate processed successfully"),
    CERT_PROCESSED_ERRONEOUSLY("CERT-PROCESSED-ERRONEOUSLY", "Certificate processing error"),
    CERT_TEMPLATES_COMPILATION_START("CERT_TEMPLATES_COMPILATION_START", "Starting compilation"),
    CERT_TEMPLATES_FROM_CACHE("CERT_TEMPLATES_FROM_CACHE", "Fetching template from cache completed"),
    CERT_TEMPLATES_HANDLEBARS_COMPILE("CERT_TEMPLATES_HANDLEBARS_COMPILE", "Template compile completed"),
    CERT_SERVER_RECEIVE("SR", "Request received, starting the processing"),
    CERT_SERVER_SEND("SS", "Processing finished, response sent");

    private final String name;
    private final String description;

    EventType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
