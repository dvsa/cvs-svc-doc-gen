package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Axle {

    @JsonProperty("Weights")
    private Weight weights;

    @JsonProperty("Tyres")
    private Tyre tyres;

    public Axle() {
    }

    public Axle(Weight weights, Tyre tyres) {
        this.weights = weights;
        this.tyres = tyres;
    }

    public Weight getWeights() {
        return weights;
    }

    public void setWeights(Weight weights) {
        this.weights = weights;
    }

    public Tyre getTyres() {
        return tyres;
    }

    public void setTyres(Tyre tyres) {
        this.tyres = tyres;
    }
}
