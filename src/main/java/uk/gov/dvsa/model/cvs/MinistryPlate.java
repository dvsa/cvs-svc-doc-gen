package uk.gov.dvsa.model.cvs;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.Document;
import uk.gov.dvsa.model.cvs.certificateData.MinistryPlateData;

public class MinistryPlate extends Document {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("PLATES_DATA")
    protected MinistryPlateData plateData;

    public String getId() {
        return id;
    }

    public MinistryPlate setId(String id) {
        this.id = id;
        return this;
    }

    public MinistryPlateData getPlateData() {
        return plateData;
    }

    public Document setPlateData(MinistryPlateData plateData) {
        this.plateData = plateData;
        return this;
    }
}
