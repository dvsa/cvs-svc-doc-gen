package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MSVA30 extends Document {
    @JsonProperty("dummyProperty")
    private String dummyProperty;

    public String getDummyProperty() {
        return dummyProperty;
    }
    public MSVA30 setDummyProperty(String dummyProperty) {
        this.dummyProperty = dummyProperty;
        return this;
    }
}

