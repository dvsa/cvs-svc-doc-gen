package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyre {

    @JsonProperty("TyreSize")
    private String tyreSize;

    @JsonProperty("PlyRating")
    private String plyRating;

    @JsonProperty("FitmentCode")
    private String fitmentCode;

    public Tyre() {
    }

    public Tyre(String tyreSize, String plyRating, String fitmentCode) {
        this.tyreSize = tyreSize;
        this.plyRating = plyRating;
        this.fitmentCode = fitmentCode;
    }

    public String getTyreSize() {
        return tyreSize;
    }

    public void setTyreSize(String tyreSize) {
        this.tyreSize = tyreSize;
    }

    public String getPlyRating() {
        return plyRating;
    }

    public void setPlyRating(String plyRating) {
        this.plyRating = plyRating;
    }

    public String getFitmentCode() {
        if (this.fitmentCode.equals("double")) {
            return "D";
        } else if (this.fitmentCode.equals("single")) {
            return "S";
        } else {
            return "";
        }
    }

    public void setFitmentCode(String fitmentCode) {
        this.fitmentCode = fitmentCode;
    }
}