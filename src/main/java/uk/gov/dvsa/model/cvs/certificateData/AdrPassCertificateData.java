package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdrPassCertificateData {
    private final String TANK_STATEMENT_OPTION_1 = "Substances permitted under the tank code and any special provisions specified in 9 may be carried";
    private final String PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_2 = "Explosives (type 2)";
    private final String PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_3 = "Explosives (type 3)";

    @JsonProperty("ChassisNumber")
    private String chassisNumber;

    @JsonProperty("Make")
    private String make;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("RegistrationNumber")
    private String registrationNumber;

    @JsonProperty("ApplicantDetails")
    private ApplicantDetails applicantDetails;

    @JsonProperty("VehicleType")
    private String vehicleType;

    @JsonProperty("PermittedDangerousGoods")
    private String[] permittedDangerousGoods;

    @JsonProperty("BrakeEndurance")
    private boolean brakeEndurance;

    @JsonProperty("Weight")
    private String weight;

    @JsonProperty("TankManufacturer")
    private String tankManufacturer;

    @JsonProperty("Tc2InitApprovalNo")
    private String tc2InitApprovalNo;

    @JsonProperty("TankManufactureSerialNo")
    private String tankManufactureSerialNo;

    @JsonProperty("YearOfManufacture")
    private String yearOfManufacture;

    @JsonProperty("TankCode")
    private String tankCode;

    @JsonProperty("SpecialProvisions")
    private String specialProvisions;

    @JsonProperty("TankStatement")
    private TankStatement tankStatement;

    @JsonProperty("ExpiryDate")
    private String expiryDate;

    @JsonProperty("AtfNameAtfPNumber")
    private String atfNameAtfPNumber;

    public String getChassisNumber() {
        return chassisNumber;
    }

    public AdrPassCertificateData setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
        return this;
    }

    public String getMake() {
        return make;
    }

    public AdrPassCertificateData setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public AdrPassCertificateData setModel(String model) {
        this.model = model;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public AdrPassCertificateData setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public ApplicantDetails getApplicantDetails() {
        return applicantDetails;
    }

    public AdrPassCertificateData setApplicantDetails(ApplicantDetails applicantDetails) {
        this.applicantDetails = applicantDetails;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public AdrPassCertificateData setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public String[] getPermittedDangerousGoods() {
        return permittedDangerousGoods;
    }

    public AdrPassCertificateData setPermittedDangerousGoods(String[] permittedDangerousGoods) {
        this.permittedDangerousGoods = permittedDangerousGoods;
        return this;
    }

    public boolean getBrakeEndurance() {
        return brakeEndurance;
    }

    public AdrPassCertificateData setBrakeEndurance(boolean brakeEndurance) {
        this.brakeEndurance = brakeEndurance;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public AdrPassCertificateData setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getTankManufacturer() {
        return tankManufacturer;
    }

    public AdrPassCertificateData setTankManufacturer(String tankManufacturer) {
        this.tankManufacturer = tankManufacturer;
        return this;
    }

    public String getTc2InitApprovalNo() {
        return tc2InitApprovalNo;
    }

    public AdrPassCertificateData setTc2InitApprovalNo(String tc2InitApprovalNo) {
        this.tc2InitApprovalNo = tc2InitApprovalNo;
        return this;
    }

    public String getTankManufactureSerialNo() {
        return tankManufactureSerialNo;
    }

    public AdrPassCertificateData setTankManufactureSerialNo(String tankManufactureSerialNo) {
        this.tankManufactureSerialNo = tankManufactureSerialNo;
        return this;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public AdrPassCertificateData setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public String getTankCode() {
        return tankCode;
    }

    public AdrPassCertificateData setTankCode(String tankCode) {
        this.tankCode = tankCode;
        return this;
    }

    public String getSpecialProvisions() {
        return specialProvisions;
    }

    public AdrPassCertificateData setSpecialProvisions(String specialProvisions) {
        this.specialProvisions = specialProvisions;
        return this;
    }

    public TankStatement getTankStatement() {
        return tankStatement;
    }

    public AdrPassCertificateData setTankStatement(TankStatement tankStatement) {
        this.tankStatement = tankStatement;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public AdrPassCertificateData setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getAtfNameAtfPNumber() {
        return atfNameAtfPNumber;
    }

    public AdrPassCertificateData setAtfNameAtfPNumber(String atfNameAtfPNumber) {
        this.atfNameAtfPNumber = atfNameAtfPNumber;
        return this;
    }

    public String getFormattedPermittedDangerousGoods() {
        String formattedPermittedDangerousGoods = "";
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            formattedPermittedDangerousGoods = permittedDangerousGood + "     ";
        }
        return formattedPermittedDangerousGoods;
    }

    public boolean getFormattedSubstancesPermitted() { // returns true for the first value that tankStatement can have and false for the other one so it can be processed in view
        if(this.tankStatement.getStatement().equals(TANK_STATEMENT_OPTION_1)){
            return true;
        } else {
            return false;
        }
    }

    public boolean getIsExplosivesType2() {
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            if(permittedDangerousGood.equals(PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_2)){
                return true;
            }
        }
        return false;
    }

    public boolean getIsExplosivesType3() {
        for (String permittedDangerousGood : this.permittedDangerousGoods) {
            if(permittedDangerousGood.equals(PERMITTED_DANGEROUS_GOODS_EXPLOSIVES_3)){
                return true;
            }

        }
        return false;
    }
}
