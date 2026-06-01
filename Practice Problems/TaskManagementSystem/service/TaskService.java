package TaskManagementSystem.service;

import TaskManagementSystem.Notification.NotificationService;
import TaskManagementSystem.Repository.TaskRepository;
import TaskManagementSystem.Repository.UserRepository;
import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.enums.TaskStatus;
import TaskManagementSystem.models.Task;


import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) {
                Task task = new Task(title, description, priority,dueDate);
                taskRepository.save(task);
                notificationService.notifyObservers(task, TaskEventType.CREATED);
                return task;
    }

    public Task updateStatus(String taskId, TaskStatus status) {
        Task task = findTaskOrThrow(taskId);
     //doenst unblock diff threads working on diff objects, but it will block threads trying
        // to update the same task at the same time. This is a common approach to
        // handle concurrent updates in a multi-threaded environment, ensuring data consistency while allowing for concurrent operations on different tasks.
        //to handle concurrent updates to the same task, we can synchronize on the task object.
        // This ensures that only one thread can update the status of the task at a time, preventing race condition
        synchronized (task) {//syn mutating part
            task.setTaskStatus(status);
            taskRepository.save(task);
        }
        notificationService.notifyObservers(task, TaskEventType.STATUS_CHANGED);
        return task;
    }

    public Task assignTask(String taskId, String assigneeId){
        Task task = findTaskOrThrow(taskId);

        validateAssignee(assigneeId);

        synchronized (task) {
            task.setAssigneeId(assigneeId);
            taskRepository.save(task);
        }
        notificationService.notifyObservers(task, TaskEventType.ASSIGNED);
        return task;

    }

    private void validateAssignee(String assigneeId) {
        userRepository.findById(assigneeId).orElseThrow(()-> new IllegalArgumentException("Assignee "+
                assigneeId + "not found"));
    }

    private Task findTaskOrThrow(String taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));
    }

    public List<Task> getTasks(TaskFilter  filter){
        return List.copyOf(taskRepository.findAll(filter));//unmodifiable
    }

    public void deleteTask(String taskId){
            Task task = findTaskOrThrow(taskId);
            taskRepository.deleteById(taskId);
            notificationService.notifyObservers(task, TaskEventType.DELETED);
    }
}
