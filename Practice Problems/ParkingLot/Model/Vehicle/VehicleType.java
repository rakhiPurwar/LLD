package ParkingLot.Model.Vehicle;

public enum VehicleType {


    CAR(30),
    TRUCK(40),
    BIKE(20);

    private final double price;
    //enum constructors are always private
    VehicleType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
