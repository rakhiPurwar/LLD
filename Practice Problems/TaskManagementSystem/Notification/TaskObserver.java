package TaskManagementSystem.Notification;

import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.models.Task;


//TaskObserver method called whn some event happens
// This is an interface that defines a method onTaskEvent which will be called when a task event happens. The method takes a Task object and a TaskEventType enum as parameters. The Task object contains the details of the task that triggered the event, and the TaskEventType enum
// indicates the type of event that occurred (e.g., TASK_CREATED, TASK_UPDATED, TASK_DELETED).
public interface TaskObserver {
    void onTaskEvent(Task task, TaskEventType eventType);
}
