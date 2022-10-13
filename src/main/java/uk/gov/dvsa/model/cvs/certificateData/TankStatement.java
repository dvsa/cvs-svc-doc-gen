package uk.gov.dvsa.model.cvs.certificateData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TankStatement {

    @JsonProperty
    private String substancesPermitted;

    @JsonProperty
    private String statement;

    @JsonProperty
    private String town;

    @JsonProperty
    private String productList;

    @JsonProperty
    private String[] productListUnNo;


    public TankStatement() {
    }

    public TankStatement(String substancesPermitted, String statement, String productList, String[] productListUnNo) {
        this.substancesPermitted = substancesPermitted;
        this.statement = statement;
        this.town = town;
        this.productList = productList;
        this.productListUnNo = productListUnNo;
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

    public void setProductListUnNo(String[] productListUnNo) {
        this.productListUnNo = productListUnNo;
    }

    public String getProductListUnNo() {
        if(this.productListUnNo == null) return "";
        return String.join(", ", this.productListUnNo);
    }
}
