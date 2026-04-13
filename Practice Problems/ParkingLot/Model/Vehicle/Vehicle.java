package ParkingLot.Model.Vehicle;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType vehicleType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    protected Vehicle(String vehicleNumBer, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumBer;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

}
