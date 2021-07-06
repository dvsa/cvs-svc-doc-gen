package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinistryPlateData {

    @JsonProperty("PlateSerialNumber")
    private String plateSerialNumber;

    @JsonProperty("DtpNumber")
    private String dtpNumber;

    @JsonProperty("PrimaryVrm")
    private String primaryVrm;

    @JsonProperty("Vin")
    private String vin;

    @JsonProperty("VariantNumber")
    private String variantNumber;

    @JsonProperty("ApprovalTypeNumber")
    private String approvalTypeNumber;

    @JsonProperty("Make")
    private String make;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("SpeedLimiterMrk")
    private String speedLimiterMrk;

    @JsonProperty("FunctionCode")
    private String functionCode;

    @JsonProperty("RegnDate")
    private String regnDate;

    @JsonProperty("ManufactureYear")
    private String manufactureYear;

    @JsonProperty("GrossGbWeight")
    private String grossGbWeight;

    @JsonProperty("GrossEecWeight")
    private String grossEecWeight;

    @JsonProperty("GrossDesignWeight")
    private String grossDesignWeight;

    @JsonProperty("TrainGbWeight")
    private String trainGbWeight;

    @JsonProperty("TrainEecWeight")
    private String trainEecWeight;

    @JsonProperty("TrainDesignWeight")
    private String trainDesignWeight;

    @JsonProperty("MaxTrainGbWeight")
    private String maxTrainGbWeight;

    @JsonProperty("MaxTrainEecWeight")
    private String maxTrainEecWeight;

    @JsonProperty("MaxLoadOnCoupling")
    private String maxLoadOnCoupling;

    @JsonProperty("FrontAxleTo5thWheelCouplingMin")
    private String frontAxleTo5thWheelCouplingMin;

    @JsonProperty("FrontAxleTo5thWheelCouplingMax")
    private String frontAxleTo5thWheelCouplingMax;

    @JsonProperty("CouplingCenterToRearTrlMax")
    private String couplingCenterToRearTrlMax;

    @JsonProperty("CouplingCenterToRearTrlMin")
    private String couplingCenterToRearTrlMin;

    @JsonProperty("PlateIssueDate")
    private String plateIssueDate;

    @JsonProperty("TyreUseCode")
    private String tyreUseCode;

    @JsonProperty("DimensionLength")
    private String dimensionLength;

    @JsonProperty("DimensionWidth")
    private String dimensionWidth;

    @JsonProperty("Axles")
    private Axles axles;

    public String getPlateSerialNumber() {
        return plateSerialNumber;
    }

    public MinistryPlateData setPlateSerialNumber(String plateSerialNumber) {
        this.plateSerialNumber = plateSerialNumber;
        return this;
    }

    public String getDtpNumber() {
        return dtpNumber;
    }

    public MinistryPlateData setDtpNumber(String dtpNumber) {
        this.dtpNumber = dtpNumber;
        return this;
    }

    public String getPrimaryVrm() {
        return primaryVrm;
    }

    public MinistryPlateData setPrimaryVrm(String primaryVrm) {
        this.primaryVrm = primaryVrm;
        return this;
    }

    public String getVin() {
        return vin;
    }

    public MinistryPlateData setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getVariantNumber() {
        return variantNumber;
    }

    public MinistryPlateData setVariantNumber(String variantNumber) {
        this.variantNumber = variantNumber;
        return this;
    }

    public String getApprovalTypeNumber() {
        return approvalTypeNumber;
    }

    public MinistryPlateData setApprovalTypeNumber(String approvalTypeNumber) {
        this.approvalTypeNumber = approvalTypeNumber;
        return this;
    }

    public String getMake() {
        return make;
    }

    public MinistryPlateData setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public MinistryPlateData setModel(String model) {
        this.model = model;
        return this;
    }

    public String getSpeedLimiterMrk() {
        return speedLimiterMrk;
    }

    public MinistryPlateData setSpeedLimiterMrk(String speedLimiterMrk) {
        this.speedLimiterMrk = speedLimiterMrk;
        return this;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public MinistryPlateData setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
        return this;
    }

    public String getRegnDate() {
        return regnDate;
    }

    public MinistryPlateData setRegnDate(String regnDate) {
        this.regnDate = regnDate;
        return this;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public MinistryPlateData setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
        return this;
    }

    public String getGrossGbWeight() {
        return grossGbWeight;
    }

    public MinistryPlateData setGrossGbWeight(String grossGbWeight) {
        this.grossGbWeight = grossGbWeight;
        return this;
    }

    public String getGrossEecWeight() {
        return grossEecWeight;
    }

    public MinistryPlateData setGrossEecWeight(String grossEecWeight) {
        this.grossEecWeight = grossEecWeight;
        return this;
    }

    public String getGrossDesignWeight() {
        return grossDesignWeight;
    }

    public MinistryPlateData setGrossDesignWeight(String grossDesignWeight) {
        this.grossDesignWeight = grossDesignWeight;
        return this;
    }

    public String getTrainGbWeight() {
        return trainGbWeight;
    }

    public MinistryPlateData setTrainGbWeight(String trainGbWeight) {
        this.trainGbWeight = trainGbWeight;
        return this;
    }

    public String getTrainEecWeight() {
        return trainEecWeight;
    }

    public MinistryPlateData setTrainEecWeight(String trainEecWeight) {
        this.trainEecWeight = trainEecWeight;
        return this;
    }

    public String getTrainDesignWeight() {
        return trainDesignWeight;
    }

    public MinistryPlateData setTrainDesignWeight(String trainDesignWeight) {
        this.trainDesignWeight = trainDesignWeight;
        return this;
    }

    public String getMaxTrainGbWeight() {
        return maxTrainGbWeight;
    }

    public MinistryPlateData setMaxTrainGbWeight(String maxTrainGbWeight) {
        this.maxTrainGbWeight = maxTrainGbWeight;
        return this;
    }

    public String getMaxTrainEecWeight() {
        return maxTrainEecWeight;
    }

    public MinistryPlateData setMaxTrainEecWeight(String maxTrainEecWeight) {
        this.maxTrainEecWeight = maxTrainEecWeight;
        return this;
    }

    public String getMaxLoadOnCoupling() {
        return maxLoadOnCoupling;
    }

    public MinistryPlateData setMaxLoadOnCoupling(String maxLoadOnCoupling) {
        this.maxLoadOnCoupling = maxLoadOnCoupling;
        return this;
    }

    public String getFrontAxleTo5thWheelCouplingMin() {
        return frontAxleTo5thWheelCouplingMin;
    }

    public MinistryPlateData setFrontAxleTo5thWheelCouplingMin(String frontAxleTo5thWheelCouplingMin) {
        this.frontAxleTo5thWheelCouplingMin = frontAxleTo5thWheelCouplingMin;
        return this;
    }

    public String getFrontAxleTo5thWheelCouplingMax() {
        return frontAxleTo5thWheelCouplingMax;
    }

    public MinistryPlateData setFrontAxleTo5thWheelCouplingMax(String frontAxleTo5thWheelCouplingMax) {
        this.frontAxleTo5thWheelCouplingMax = frontAxleTo5thWheelCouplingMax;
        return this;
    }

    public String getCouplingCenterToRearTrlMax() {
        return couplingCenterToRearTrlMax;
    }

    public MinistryPlateData setCouplingCenterToRearTrlMax(String couplingCenterToRearTrlMax) {
        this.couplingCenterToRearTrlMax = couplingCenterToRearTrlMax;
        return this;
    }

    public String getCouplingCenterToRearTrlMin() {
        return couplingCenterToRearTrlMin;
    }

    public MinistryPlateData setCouplingCenterToRearTrlMin(String couplingCenterToRearTrlMin) {
        this.couplingCenterToRearTrlMin = couplingCenterToRearTrlMin;
        return this;
    }

    public String getPlateIssueDate() {
        return plateIssueDate;
    }

    public MinistryPlateData setPlateIssueDate(String plateIssueDate) {
        this.plateIssueDate = plateIssueDate;
        return this;
    }

    public String getTyreUseCode() {
        return tyreUseCode;
    }

    public MinistryPlateData setTyreUseCode(String tyreUseCode) {
        this.tyreUseCode = tyreUseCode;
        return this;
    }

    public String getDimensionLength() {
        return dimensionLength;
    }

    public MinistryPlateData setDimensionLength(String dimensionLength) {
        this.dimensionLength = dimensionLength;
        return this;
    }

    public String getDimensionWidth() {
        return dimensionWidth;
    }

    public MinistryPlateData setDimensionWidth(String dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
        return this;
    }

    public Axles getAxles() {
        return axles;
    }

    public MinistryPlateData setAxles(Axles axles) {
        this.axles = axles;
        return this;
    }
}
