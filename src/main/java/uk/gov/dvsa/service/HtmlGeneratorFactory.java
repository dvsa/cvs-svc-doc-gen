package uk.gov.dvsa.service;

import com.github.jknack.handlebars.Handlebars;

public class HtmlGeneratorFactory {
    public HtmlGenerator create(String documentName) {
        Handlebars handlebars = new Handlebars();
        return new HtmlGenerator(handlebars);
    }
}
