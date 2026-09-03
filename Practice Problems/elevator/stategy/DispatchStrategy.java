package elevator.stategy;

import elevator.dto.Elevator;
import elevator.dto.Request;

import java.util.List;

public interface DispatchStrategy {
    Elevator selectOptimalElevator(List<Elevator> elevators, Request request);
}
