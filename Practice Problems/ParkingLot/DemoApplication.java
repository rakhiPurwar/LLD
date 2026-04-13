package ParkingLot;

import ParkingLot.Model.Parking.ParkingFloor;
import ParkingLot.Model.Parking.ParkingSpot;
import ParkingLot.Model.Ticket.Ticket;
import ParkingLot.Model.Vehicle.Bike;
import ParkingLot.Model.Vehicle.Car;
import ParkingLot.Model.Vehicle.Vehicle;
import ParkingLot.Model.Vehicle.VehicleType;
import ParkingLot.Strategy.HourlyPricingStrategy;
import ParkingLot.Strategy.PricingStrategy;
import ParkingLot.services.ParkingLotService;

import java.util.List;

public class DemoApplication {
    ParkingSpot spot1 = new ParkingSpot("000", VehicleType.CAR);
    ParkingSpot spot2 = new ParkingSpot("001", VehicleType.BIKE);
    ParkingSpot spot3 = new ParkingSpot("002", VehicleType.TRUCK);

    ParkingSpot spot4 = new ParkingSpot("003", VehicleType.BIKE);

    ParkingFloor groundFloor = new ParkingFloor("Ground", List.of(spot1,spot2));
    ParkingFloor firstFloor = new ParkingFloor("First", List.of(spot3,spot4));

    PricingStrategy strategy = new HourlyPricingStrategy();

    ParkingLotService parkingLotService = new ParkingLotService(List.of(groundFloor,firstFloor),strategy);
    ParkingLotApplication application = new ParkingLotApplication(parkingLotService);

    Vehicle bike = new Bike("ABC00");
    Vehicle car = new Car("ABC01");

    Ticket ticket1 = application.parkVehicle(car);

    Ticket ticket2 = application.parkVehicle(bike);





}
