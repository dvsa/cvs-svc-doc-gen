package uk.gov.dvsa.model.mot.enums;

public enum SkeletalVehicleTypes {

    RIGID("Rigid skeletal"),
    FULL_DRAWBAR("Full drawbar skeletal"),
    CENTRE_AXLE("Centre axle skeletal"),
    SEMI_TRAILER("Semi trailer skeletal");

    private final String vehicleType;

    SkeletalVehicleTypes(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
