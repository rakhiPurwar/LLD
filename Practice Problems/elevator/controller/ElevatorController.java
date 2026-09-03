package elevator.controller;

import elevator.dto.Direction;
import elevator.dto.Elevator;
import elevator.dto.Request;
import elevator.stategy.DispatchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElevatorController {
    private final List<Elevator> elevators;
    private DispatchStrategy dispatchStrategy;

    public ElevatorController(int numElevators, DispatchStrategy strategy) {
        this.elevators = new ArrayList<>();
        this.dispatchStrategy = strategy;

        for (int i =1; i <= numElevators; i++) {
            Elevator elevator = new Elevator(i);
            this.elevators.add(new Elevator(i));
            new Thread(elevator).start();
        }
    }

        public void setDispatchStrategy(DispatchStrategy strategy) {
            this.dispatchStrategy = strategy;
        }

        public void submitExternalRequest(int floor, Direction direction) {
            Request request = new Request(floor,direction);
            Elevator selectedElevator = dispatchStrategy.selectOptimalElevator(elevators,request);
            System.out.println("Externa/l request for floor " + floor + " in direction " + direction + " assigned to Elevator " + selectedElevator.getId());

            selectedElevator.addRequest(request);
       }

       public void submitInternalRequest(int elevatorId, int floor) {
           Optional<Elevator> elevatorOpt = elevators.stream().filter(e -> e.getId() == elevatorId).findFirst();
           if (elevatorOpt.isPresent()) {
               Elevator elevator = elevatorOpt.get();
               Direction dir = floor > elevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;
               Request request = new Request(floor, dir);
               elevator.addRequest(request);
               System.out.println("Internal request for floor " + floor + " added to Elevator " + elevatorId);
           } else {
               System.err.println("Elevator with ID " + elevatorId + " not found.");
           }
       }
}
