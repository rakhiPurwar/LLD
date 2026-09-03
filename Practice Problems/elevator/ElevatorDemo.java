package elevator;
import java.util.*;
import elevator.controller.ElevatorController;
import elevator.dto.Direction;
import elevator.stategy.NearestElevatorStrategy;

public class ElevatorDemo {
    public static void main(String args[]) throws InterruptedException {
        ElevatorController controller = new ElevatorController(2, new NearestElevatorStrategy());
        System.out.println("---Elevator System Simulation");

        controller.submitExternalRequest(5, Direction.UP);
        controller.submitExternalRequest(2, Direction.DOWN);

        Thread.sleep(1000);
        controller.submitInternalRequest(1, 8);

        Thread.sleep(1000);
    }

}
