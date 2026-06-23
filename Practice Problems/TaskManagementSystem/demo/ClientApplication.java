package TaskManagementSystem.demo;

import TaskManagementSystem.Notification.EmailNotifier;
import TaskManagementSystem.Notification.NotificationService;
import TaskManagementSystem.Repository.InMemorytaskRepository;
import TaskManagementSystem.Repository.InMemoryUserRepository;
import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;
import TaskManagementSystem.models.Task;
import TaskManagementSystem.models.User;
import TaskManagementSystem.service.TaskService;

import java.time.LocalDate;
import java.util.List;

public class ClientApplication {
    public static void main(String[] args) {
        // Here we can create an instance of TaskManager and perform some operations to demonstrate the functionality of the task management system.
        // For example, we can create a new task, update its status, and then delete it.

        // Create notifications service
        InMemorytaskRepository taskRepo = new InMemorytaskRepository();
        InMemoryUserRepository userRepo = new InMemoryUserRepository() ;
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new EmailNotifier());
        TaskService taskService = new TaskService(taskRepo, userRepo, notificationService);

        // 2 -> create users

        User rakhi = new User("Rakhi", "rpurwar20");
        User pawan = new User("Pawan", "pawan20");
        userRepo.save(rakhi);//client should not directly interact with user repo, shortage of time
        userRepo.save(pawan);

        System.out.println("Users created");

        // 3 -> create task

        Task task1 = taskService.createTask("Design db",
                "Design the database schema for the new project", Priority.HIGH,
                LocalDate.now().plusDays(7));

        Task task2 = taskService.createTask("Implement API",
                "Develop the RESTful API for the new project", Priority.MEDIUM,
                LocalDate.now().plusDays(14));

        taskService.assignTask(task1.getTaskId(), rakhi.getUserId());
        taskService.assignTask(task2.getTaskId(), pawan.getUserId());

        taskService.updateStatus(task1.getTaskId(),TaskStatus.IN_PROGRESS);
        taskService.updateStatus(task2.getTaskId(),TaskStatus.IN_PROGRESS);
        taskService.updateStatus(task1.getTaskId(),TaskStatus.COMPLETED);

        TaskFilter inProgressFilter = new TaskFilter.Builder()
                .status(TaskStatus.IN_PROGRESS)
                .build();

        List<Task>inProgress = taskService.getTasks(inProgressFilter);
        System.out.println("In Progress Tasks:");
        for(Task task : inProgress){
            System.out.println(task.getTitle() + " assigned to " + task.getAssigneeId());
        }

    }
}
