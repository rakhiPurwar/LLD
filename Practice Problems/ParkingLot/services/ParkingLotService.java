package ParkingLot.services;

import ParkingLot.Exceptiom.ParkingException;
import ParkingLot.Model.Parking.ParkingFloor;
import ParkingLot.Model.Parking.ParkingSpot;
import ParkingLot.Model.Ticket.Ticket;
import ParkingLot.Model.Vehicle.Vehicle;
import ParkingLot.Model.Vehicle.VehicleType;
import ParkingLot.Strategy.PricingStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


//sirf main main functions rakhenege interaction wale, dont make this heavy
public class ParkingLotService {
    private final Map<String, Ticket> activeTickets;
    private final List<ParkingFloor> parkingFloors;
    private PricingStrategy pricingStrategy;


    /*
     ---
  "A parking lot has multiple entry and exit gates running simultaneously — each gate is a separate thread. All threads share one activeTickets map."

  ---
  Problem with HashMap

  1. Concurrent puts corrupt the map
  ▎ "When two threads call put() simultaneously and both keys hash to the same bucket, they traverse and modify the same linked list concurrently. One thread can overwrite
  another's next pointer — causing data loss or infinite loops."

  2. Resize is catastrophic
  ▎ "When HashMap reaches 75% capacity, it doubles its size and rehashes all entries. If two threads trigger this simultaneously, both create separate new arrays and move entries
  independently. When one overwrites the other, all entries moved by the first thread are permanently lost."

  ---
  Why ConcurrentHashMap

  1. Bucket-level locking
  ▎ "Instead of locking the whole map, CHM locks only the specific bucket being modified. So Thread A writing to bucket 3 doesn't block Thread B writing to bucket 7 — true
  parallelism."

  2. Hash collision is still safe
  ▎ "If two different ticket IDs hash to the same bucket, CHM locks that bucket's head node. Only one thread modifies that linked list at a time — no pointer corruption."

  3. Reads are free
  ▎ "get() and containsKey() acquire no locks at all — reads are fully concurrent with writes."

  ---
  Bonus — atomic operations matter too

  ▎ "ConcurrentHashMap alone isn't enough. In unparkVehicle(), doing containsKey() then remove() separately is still a race condition —
  another thread can remove the ticket
  between the two calls. The fix is using remove() alone and checking if it returns null — that's one atomic
  operation with no gap."
  also it may happen that card and bike both are making entry at same,or one entry another exit,
  so we have to modify hashmap at same time which is not possible
  any kind of modifications or writes in a multithreaded system must be accompanied by thread safety
     */
    public ParkingLotService(List<ParkingFloor> parkingFloors, PricingStrategy pricingStrategy) {
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingFloors = parkingFloors;
        this.pricingStrategy = pricingStrategy;
    }



    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle.getVehicleType());
        if(!spot.parkVehicle(vehicle)){
            throw new ParkingException("Unable to park"+ vehicle.getVehicleNumber());
        }
        Ticket ticket = new Ticket(spot);
        Ticket existing = activeTickets.putIfAbsent(ticket.getTicketId(),ticket);
        if (existing != null) {
            parkVehicle(vehicle);
        }

        return ticket;

    }

    public double unparkVehicle(Ticket ticket) {

        ticket.setExitTime(java.time.LocalDateTime.now());
        double charges = pricingStrategy.calculateCharges(ticket);
        ticket.closeTicket(charges);
        ticket.getSpot().removeVehicle(); //ticket lo usse spot phr remove kro
        activeTickets.remove(ticket.getTicketId());
        return charges;

    }

    private ParkingSpot findAvailableSpot(VehicleType vehicleType) {
//nearest parking spot
        for (ParkingFloor parkingFloor : parkingFloors) {
            for (ParkingSpot spot : parkingFloor.getParkingSpot()) {
                if (spot.getVehicleType() == vehicleType && spot.isSpotAvailable()) {
                    return spot;
                }
            }
        }
        throw new ParkingException("No spot available" + vehicleType);
    }
}
