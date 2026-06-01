package TaskManagementSystem.models;

import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final String taskId;
    private final String title;
    private final String description;
    private TaskStatus taskStatus;
    private Priority priority;
    private String assigneeId;
    private LocalDate dueDate;

    public Task(String title, String description, Priority priority, LocalDate dueDate) {
        this.taskId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.taskStatus = TaskStatus.TODO;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setTaskStatus(TaskStatus taskStatus){

        /// state machine guard - discuss in interview
        if(this.taskStatus == TaskStatus.COMPLETED){
            throw new IllegalStateException("Cannot change status of completed task");
        }
        this.taskStatus = taskStatus;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

}
