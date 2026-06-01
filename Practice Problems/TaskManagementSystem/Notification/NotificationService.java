package TaskManagementSystem.Notification;

import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.models.Task;
import com.sun.source.util.TaskEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();

    //Executor service for async notification dispatch
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    // This method allows us to add an observer to the list of observers that will receive notifications when a task event happens. The CopyOnWriteArrayList is used to ensure thread safety when adding or removing observers.
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    //fires async - calling thread is not blocked

    //parallel run so that throughput is high
    public void notifyObservers(Task task, TaskEventType taskEventType) {
        for (TaskObserver observer : observers) {
            executor.submit(() -> {
                try {
                    observer.onTaskEvent(task, taskEventType);
                } catch (Exception e) {
                    System.err.println("Error notifying observer: " + e.getMessage());
                }//for the interview printing on console

            });
        }

    }

    public void shutdown(){
    executor.shutdown();
    }

}
