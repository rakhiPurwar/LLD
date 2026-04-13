package ParkingLot.Strategy;

import ParkingLot.Model.Ticket.Ticket;

import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy{

    private int fixedPrice = 20;

    @Override
    public Double calculateCharges(Ticket ticket) {
        long hours = Duration.between(ticket.getEntryTime(),ticket.getExitTime()).toHours();
        return (hours*ticket.getSpot().getVehicleType().getPrice()+ fixedPrice);
    }
}
