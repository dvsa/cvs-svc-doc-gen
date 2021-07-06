package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weight {

    @JsonProperty("GbWeight")
    private String gbWeight;

    @JsonProperty("DesignWeight")
    private String designWeight;

    @JsonProperty("EecWeight")
    private String eecWeight;

    public Weight() {
    }

    public Weight(String gbWeight, String designWeight, String eecWeight) {
        this.gbWeight = gbWeight;
        this.designWeight = designWeight;
        this.eecWeight = eecWeight;
    }

    public String getGbWeight() {
        return gbWeight;
    }

    public void setGbWeight(String gbWeight) {
        this.gbWeight = gbWeight;
    }

    public String getDesignWeight() {
        return designWeight;
    }

    public void setDesignWeight(String designWeight) {
        this.designWeight = designWeight;
    }

    public String getEecWeight() {
        return eecWeight;
    }

    public void setEecWeight(String eecWeight) {
        this.eecWeight = eecWeight;
    }
}