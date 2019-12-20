package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TankStatement {
    @JsonProperty
    private String substancesPermitted;

    @JsonProperty
    private String statement;

    @JsonProperty
    private String town;

    @JsonProperty
    private String productList;


    public TankStatement() {
    }

    public TankStatement(String substancesPermitted, String statement, String productList) {
        this.substancesPermitted = substancesPermitted;
        this.statement = statement;
        this.town = town;
        this.productList = productList;
    }

    public String getSubstancesPermitted() {
        return substancesPermitted;
    }

    public void setSubstancesPermitted(String substancesPermitted) {
        this.substancesPermitted = substancesPermitted;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }
}
