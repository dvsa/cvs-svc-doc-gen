package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RwtCertificateData {


    @JsonProperty("Dgvw")
    private int dgvw;

    @JsonProperty("Weight2")
    private int weight2;

    @JsonProperty("VehicleNumber")
    private String vehicleNumber;

    @JsonProperty("Vin")
    private String vin;

    @JsonProperty("IssuersName")
    private String issuersName;

    @JsonProperty("DateOfInspection")
    private String dateOfInspection;

    @JsonProperty("TestStationPNumber")
    private String testStationPNumber;

    @JsonProperty("DocumentNumber")
    private String documentNumber;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Defects")
    private String[] defects;

    @JsonProperty("IsTrailer")
    private  boolean isTrailer;

    @JsonProperty("DesignTrainWeightRequired")
    private String designTrainWeightRequired;

    public int getDgvw() {
        return dgvw;
    }

    public RwtCertificateData setDgvw(int dgvw) {
        this.dgvw = dgvw;
        return this;
    }

    public int getWeight2() {
        return weight2;
    }

    public RwtCertificateData setWeight2(int weight2) {
        this.weight2 = weight2;
        return this;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public RwtCertificateData setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public String getVin() {
        return vin;
    }

    public RwtCertificateData setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getIssuersName() {
        return issuersName;
    }

    public RwtCertificateData setIssuersName(String issuersName) {
        this.issuersName = issuersName;
        return this;
    }

    public String getDateOfInspection() {
        return dateOfInspection;
    }

    public RwtCertificateData setDateOfInspection(String dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
        return this;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public RwtCertificateData setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
        return this;
    }

    public String getDocumentNumber() {return documentNumber;}
    public RwtCertificateData setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public String getDate() {return date;}
    public RwtCertificateData setDate(String date) {
        this.date = date;
        return this;
    }

    public String[] getDefects() {return this.defects;}

    public RwtCertificateData setDefects(String [] defects){
        this.defects = defects;
        return this;
    }

    public boolean getHasDefects() {
        return this.defects!=null && this.defects.length>0;
    }

    public boolean getHasNoDefects() {
        return this.defects==null || this.defects.length==0;
    }

    public boolean getIsTrailer() {return this.isTrailer;}

    public RwtCertificateData setIsTrailer(boolean isTrailer){
        this.isTrailer= isTrailer;
        return this;
    }

    public String getDesignTrainWeightRequired() { return designTrainWeightRequired; }
    public RwtCertificateData setDesignTrainWeightRequired(String designTrainWeightRequired) {
        this.designTrainWeightRequired = designTrainWeightRequired;
        return this;
    }
}
