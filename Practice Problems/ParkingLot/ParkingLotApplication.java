package ParkingLot;

import ParkingLot.Model.Ticket.Ticket;
import ParkingLot.Model.Vehicle.Vehicle;
import ParkingLot.Strategy.PricingStrategy;
import ParkingLot.services.ParkingLotService;

public class ParkingLotApplication {

    //orchestrator
    ParkingLotService parkingLotService;

    public ParkingLotApplication(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        return parkingLotService.parkVehicle(vehicle);
    }

    public double unparkVehicle(Ticket ticket){
        return parkingLotService.unparkVehicle(ticket);
    }
}
