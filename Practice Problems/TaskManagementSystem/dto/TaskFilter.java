package TaskManagementSystem.dto;

import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;

public class TaskFilter {
    private TaskStatus status;
    private String assigneeId;
    private Priority priority;

    private TaskFilter(){};

    public TaskStatus getStatus() {
        return status;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public Priority getPriority() {
        return priority;
    }

    public static class Builder {
       private final TaskFilter taskFilter  = new
               TaskFilter();

       public Builder assigneeId(String assigneeId){
           taskFilter.assigneeId = assigneeId;
           return this;
       }

       public Builder status(TaskStatus status){
           taskFilter.status = status;
           return this;
       }

       public Builder priority(Priority priority){
           taskFilter.priority = priority;
           return this;
       }

       public TaskFilter build(){
           return taskFilter;
       }



    }
}
