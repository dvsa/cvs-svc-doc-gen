package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recalls {
    @JsonProperty
    private String manufacturer;
    @JsonProperty
    private boolean hasRecall;

    public Recalls(){}

    public Recalls(String manufacturer, Boolean hasRecall){
        this.manufacturer = manufacturer;
        this.hasRecall = hasRecall;
    }

    public String getManufacturer() {return this.manufacturer;}

    public boolean isHasRecall() {return this.hasRecall;}

    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}

    public void setHasRecall(boolean hasRecall) {this.hasRecall = hasRecall;}




}
