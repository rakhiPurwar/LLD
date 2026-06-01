package TaskManagementSystem.Repository;

import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void save(Task task);
    Optional<Task> findById(String id);
    List<Task> findAll(TaskFilter taskFilter);
    void deleteById(String taskId);
}
