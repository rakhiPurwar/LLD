package ParkingLot.Model.Parking;

import java.util.List;

public class ParkingFloor {
    private String floorId;
    /* final = reference can't be reassigned, but list contents are still mutable
    now u cant do  List<ParkingSpot> = new ArrayList<>(); but inside list can be modified
    to prevent it pass copy
     */
    private final List<ParkingSpot> parkingSpot;

    public ParkingFloor(String floorId, List<ParkingSpot> parkingSpot) {
        this.floorId = floorId;
        this.parkingSpot = parkingSpot;
    }

    public String getFloorId() {
        return floorId;
    }

    /*Returns an unmodifiable copy (defensive copy) so callers can't modify internal state This is done for encapsulation
     — to prevent outside code from modifying the internal state.
     Without it (bad):
  public List<ParkingSpot> getParkingSpot() {
      return parkingSpot; // returns the actual reference
  }

  // caller can now break your object's state:
  floor.getParkingSpot().add(maliciousSpot);    // modifies internal list!
  floor.getParkingSpot().remove(0);

   With List.copyOf() (good):
  public List<ParkingSpot> getParkingSpot() {
      return List.copyOf(parkingSpot); // returns an immutable copy
  }

  // caller gets an error if they try to modify:
  floor.getParkingSpot().add(spot); // throws UnsupportedOperationException

     */
    public List<ParkingSpot> getParkingSpot() {
        return List.copyOf(parkingSpot);
    }
}

/*
  final = can't point to a different list
  List.copyOf() = caller can't modify the contents
 */