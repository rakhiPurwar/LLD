package elevator.stategy;

import elevator.dto.Direction;
import elevator.dto.Elevator;
import elevator.dto.Request;

import java.util.List;

public class NearestElevatorStrategy implements DispatchStrategy {

    @Override
    public Elevator selectOptimalElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getRequestedFloor());
            Direction direction = elevator.getCurrentDirection();

            boolean isMovingToward = (direction == Direction.UP && elevator.getCurrentFloor() <= request.getRequestedFloor()) ||
                    (direction == Direction.DOWN && elevator.getCurrentFloor() >= request.getRequestedFloor());

            if (direction == Direction.IDLE || isMovingToward) {
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        return bestElevator != null ? bestElevator : elevators.getFirst();

    }

}
