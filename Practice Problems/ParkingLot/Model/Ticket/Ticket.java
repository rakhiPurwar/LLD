package ParkingLot.Model.Ticket;

import ParkingLot.Model.Parking.ParkingSpot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double charges;

    public Ticket(ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public void closeTicket(double charges) {
        this.charges = charges;
    }

    public void setExitTime(LocalDateTime time){
        this.exitTime = time;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Double getCharges() {
        return charges;
    }
}


