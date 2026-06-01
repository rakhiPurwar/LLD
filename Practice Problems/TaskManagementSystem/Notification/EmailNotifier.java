package TaskManagementSystem.Notification;

import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.models.Task;

//use  TaskObserver to send email notifications when a task event happens. For simplicity, we will just print the notification to the console.
public class EmailNotifier implements TaskObserver{
    @Override
    public void onTaskEvent(Task task, TaskEventType eventType) {
        System.out.println("Email Notification: Task " + task.getTaskId() +  task.getTitle()+ "Assignee is+"+ task.getAssigneeId() +
                " has event " + eventType);
    }
}
