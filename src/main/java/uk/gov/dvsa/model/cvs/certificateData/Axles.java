package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Axles {

    @JsonProperty("Axle1")
    private Axle axle1;

    @JsonProperty("Axle2")
    private Axle axle2;

    @JsonProperty("Axle3")
    private Axle axle3;

    @JsonProperty("Axle4")
    private Axle axle4;

    public Axles() {
    }

    public Axles(Axle axle1, Axle axle2, Axle axle3, Axle axle4) {
        this.axle1 = axle1;
        this.axle2 = axle2;
        this.axle3 = axle3;
        this.axle4 = axle4;
    }

    public Axle getAxle1() {
        return axle1;
    }

    public void setAxle1(Axle axle1) {
        this.axle1 = axle1;
    }

    public Axle getAxle2() {
        return axle2;
    }

    public void setAxle2(Axle axle2) {
        this.axle2 = axle2;
    }

    public Axle getAxle3() {
        return axle3;
    }

    public void setAxle3(Axle axle3) {
        this.axle3 = axle3;
    }

    public Axle getAxle4() {
        return axle4;
    }

    public void setAxle4(Axle axle4) {
        this.axle4 = axle4;
    }
}
