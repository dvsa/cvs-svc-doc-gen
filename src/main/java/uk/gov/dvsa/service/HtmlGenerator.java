package uk.gov.dvsa.service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.gov.dvsa.exception.HtmlTemplateCompilationException;
import uk.gov.dvsa.exception.HtmlTemplateProcessingException;
import uk.gov.dvsa.logging.EventLogger;
import uk.gov.dvsa.logging.EventType;
import uk.gov.dvsa.logging.LoggingExecutor;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.enums.DocumentsConfig;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlGenerator {

    private static final Logger logger = LogManager.getLogger(HtmlGenerator.class);

    private static Map<String, Template> templateCache = new HashMap<>();

    private final LoggingExecutor executor = new LoggingExecutor(logger);
    private final EventLogger eventLogger = new EventLogger(logger);
    private final Handlebars handlebars;

    public HtmlGenerator(Handlebars handlebars) {
        Handlebars handlebarsWithHelpers;
        handlebarsWithHelpers = registerTabulatorHelper(handlebars);
        handlebarsWithHelpers = registerIsoDateFormatHelper(handlebars);
        handlebarsWithHelpers = registerEqualsHelper(handlebars);
        handlebarsWithHelpers = registerNotEqualsHelper(handlebars);
        this.handlebars = handlebarsWithHelpers;
    }

    public List<String> generate(Document context) {
        List<Template> templates = executor.timed(
            () -> getTemplates(context.getDocumentName()),
            EventType.CERT_TEMPLATES_COMPILATION
        );
        return executor.timed(
            () -> processTemplates(context, templates),
            EventType.CERT_HTML_GENERATION
        );
    }

    private List<Template> getTemplates(String documentName) {
        List<Template> templates = new ArrayList<>();
        String[] templateNames = DocumentsConfig.fromDocumentName(documentName).getTemplateNames();
        for (String templateName: templateNames) {
            logger.info("Compiling {} template", templateName);
            templates.add(compileTemplate(templateName));
        }
        return templates;
    }

    private Template compileTemplate(String templateName) {

        Template templateFromCache = getTemplateFromCache(templateName);
        if (templateFromCache != null) {
            eventLogger.logEvent(EventType.CERT_TEMPLATES_FROM_CACHE);
            return templateFromCache;
        }

        try {
            eventLogger.logEvent(EventType.CERT_TEMPLATES_HANDLEBARS_COMPILE);

            Template compiledTemplate = handlebars.compile(String.format("views/%s", templateName));
            addToCache(templateName, compiledTemplate);
            return compiledTemplate;
        } catch (IOException e) {
            throw new HtmlTemplateCompilationException(e);
        }
    }

    private Handlebars registerTabulatorHelper(Handlebars handlebars) {
        return handlebars.registerHelper("tabulator", (context, options) -> context.toString()
                .replaceFirst("\t(.*)", "<span class='boxes__item-padded-text'>$1</span>")
        );
    }

    private Handlebars registerIsoDateFormatHelper(Handlebars handlebars) {
        return handlebars.registerHelper("formatIsoDate", (context, options) -> {
            String input = context.toString();
            DateTimeFormatter ukFormat = DateTimeFormatter.ofPattern(options.param(0, "dd/MM/yyyy"));
            LocalDate ukDate;

            try {
                if (input.length() == 10) {
                    // likely yyyy-mm-dd
                    DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE;
                    ukDate = LocalDate.from(f.parse(input));
                } else {
                    // try a full UTC DateTime...
                    DateTimeFormatter f = DateTimeFormatter.ISO_INSTANT;
                    ukDate = LocalDate.ofInstant(Instant.from(f.parse(input)), ZoneId.of("Europe/London"));
                }

                return ukFormat.format(ukDate);
            } catch(Exception e) {
                return context;
            }
        });
    }

    private Handlebars registerEqualsHelper(Handlebars handlebars) {
        return handlebars.registerHelper("eq", (context, options) -> context.toString().equals(options.param(0).toString()));
    }

    private Handlebars registerNotEqualsHelper(Handlebars handlebars) {
        return handlebars.registerHelper("neq", (context, options) -> !context.toString().equals(options.param(0).toString()));
    }

    private List<String> processTemplates(Document context, List<Template> templates) {
        List<String> htmls = new ArrayList<>();
        try {
            for (Template template : templates) {
                htmls.add(template.apply(context));
            }
            return htmls;
        } catch (IOException e) {
            throw new HtmlTemplateProcessingException(e);
        }
    }

    private Template getTemplateFromCache(String templateName) {
        return templateCache.get(templateName);
    }

    private void addToCache(String templateName, Template template) {
        templateCache.put(templateName, template);
    }
}
