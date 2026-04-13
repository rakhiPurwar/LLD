package ParkingLot.Strategy;

import ParkingLot.Model.Ticket.Ticket;

public interface PricingStrategy {
    Double calculateCharges(Ticket ticket);

}
