package elevator.dto;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


//represents
//every elevator is a thread, runs on its own thread, only responsible for its own movemment
public class Elevator implements Runnable {
    private final int id;
    //use of atomic integer kyunki multiple threads can access it concurrently without issues
    //final prevents reassignment of the reference, not modification of
    // the object's internal state. AtomicReference is still mutable,
    // so calling set() changes the value it holds while keeping the same object reference.
    private final AtomicInteger currentFloor;
    private final AtomicReference<Direction> currentDirection;

    //ConcurrentSkipListSet is a thread-safe sorted set that allows concurrent access and
    // maintains the order of elements. It is used here to store the requested floors for
    // up and down directions, ensuring that requests are processed in a sorted manner.
    //o(logn) time cost for basic operations
    //sorted
    //+
    //thread-safe
    //+
    //no duplicate floors

    private final ConcurrentSkipListSet<Integer> upRequests;
    private final ConcurrentSkipListSet<Integer> downRequests;



    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(0); // Assuming ground floor as starting point
        this.currentDirection = new AtomicReference<>(Direction.IDLE);
        this.upRequests = new ConcurrentSkipListSet<>();
        this.downRequests = new ConcurrentSkipListSet<>((a,b)->Integer.compare(b,a));

    }

    //called by internal buttons of dispatcher

    public void addRequest(Request request){
        if(request.getDirection() == Direction.UP){
            upRequests.add(request.getRequestedFloor());
        }else if(request.getDirection() == Direction.DOWN){
            downRequests.add(request.getRequestedFloor());
        }

        if(currentDirection.get()==Direction.IDLE){
            currentDirection.set(request.getRequestedFloor() > currentFloor.get() ? Direction.UP:Direction.DOWN);
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                processRequests();
                Thread.sleep(1000);//simulate movement time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Elevator "+ id + " interrupted.");
                break;
            }
        }
    }

    private void processRequests() throws InterruptedException {
        if(currentDirection.get() == Direction.UP || currentDirection.get() == Direction.IDLE){
            processUpRequests();
            procesDownsRequests();
        }else{
            procesDownsRequests();
            processUpRequests();
        }
    }

    private void procesDownsRequests() throws InterruptedException {
        while (!downRequests.isEmpty()){
            int nextFloor = downRequests.pollLast(); ///get and remove highest floor
            moveToFloor(nextFloor);
        }

        if(!upRequests.isEmpty()){
            currentDirection.set(Direction.UP);
        }

    }

    private void processUpRequests() throws InterruptedException {
        while (!upRequests.isEmpty()){
            int nextFloor = upRequests.pollFirst(); ///get and remove lowest floor
            moveToFloor(nextFloor);
        }

        if(!downRequests.isEmpty()){
            currentDirection.set(Direction.DOWN);
        }
    }

    private void moveToFloor(int targetFloor) throws InterruptedException {
        System.out.println("Elevator "+ id + "moving from " +  currentFloor.get() + " to " + targetFloor);
        Thread.sleep(Math.abs(currentFloor.get()-targetFloor) * 500L);//simulate movement time, 500ms per floor
        currentFloor.set(targetFloor);
        System.out.println("Elevator "+ id + "arrived at floor " +  currentFloor.get());
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public Direction getCurrentDirection() {
        return currentDirection.get();
    }
}
