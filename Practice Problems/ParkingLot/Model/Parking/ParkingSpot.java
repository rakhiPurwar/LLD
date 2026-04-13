package ParkingLot.Model.Parking;

import ParkingLot.Model.Vehicle.Vehicle;
import ParkingLot.Model.Vehicle.VehicleType;

public class ParkingSpot {
    private final String spotId;
    private final VehicleType vehicleType ;
    private Vehicle vehicle;

    public ParkingSpot(String spotId, VehicleType vehicleType) {
        this.spotId = spotId;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean parkVehicle(Vehicle vehicle){
     if(!isSpotAvailable()) return false;//if no spot

     if(vehicle.getVehicleType()!=vehicleType){ //if vehicle type does
         return false;
     }

     this.vehicle = vehicle;
     return true;
    }

    public synchronized void removeVehicle(){
        this.vehicle = null;
    }

    public boolean isSpotAvailable(){
        return vehicle == null;
    }

    public String getId() {
        return spotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }


}
