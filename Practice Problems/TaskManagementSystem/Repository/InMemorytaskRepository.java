package TaskManagementSystem.Repository;

import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.models.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorytaskRepository implements TaskRepository{
    private final Map<String,Task> tasks = new ConcurrentHashMap<>();
    @Override
    public void save(Task task) {
        tasks.put(task.getTaskId(),task);
    }

    @Override
    public Optional<Task> findById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    //use stream api to filter tasks based on the criteria in taskFilter. If a criteria is null, it means we don't want to filter by that criteria.
    @Override
    public List<Task> findAll(TaskFilter taskFilter) {
        return tasks.values().stream()
                .filter(t->taskFilter.getAssigneeId() == null || t.getAssigneeId().equals(taskFilter.getAssigneeId()))
                .filter(t->taskFilter.getPriority() == null || t.getPriority().equals(taskFilter.getPriority()))
                .filter(t->taskFilter.getStatus() == null || t.getTaskStatus().equals(taskFilter.getStatus()))
                .toList();
    }


    @Override
    public void deleteById(String taskId) {
        tasks.remove(taskId);
    }
}
