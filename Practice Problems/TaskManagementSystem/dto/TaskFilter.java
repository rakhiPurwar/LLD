package TaskManagementSystem.dto;

import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;

//dto because ye filtering criteria hai, not the actual task object. Task object me to title, description, due date etc. honge, but yaha sirf filter criteria hai.
// interviewer puuche toh batao ki ye ek DTO hai jo task filtering criteria ko represent karta hai, aur isme builder pattern use kiya gaya hai taaki hum easily filter criteria set kar sakein jab hum tasks ko query karte hain.
//I used Builder pattern for TaskFilter because filter criteria are optional and may grow in future.
//Builder = optional fields.
public class TaskFilter {
    private TaskStatus status;
    private String assigneeId;
    private Priority priority;

    //constructor private.Object creation is controlled through Builder.
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

   //static builder class inside same
    //static class kyu? kyuki hum TaskFilter.Builder ko directly use karna chahte hain bina kisi instance ke. Agar Builder non-static hota, toh hume pehle TaskFilter ka instance create karna padta aur phir uske through Builder ko access karna padta. Static class allow karti hai ki hum directly TaskFilter.Builder ko access kar sakein bina kisi TaskFilter instance ke.
    public static class Builder {
        //final kyuki hum chahte hain ki ek hi instance of TaskFilter create ho aur uske fields ko set kiya jaaye. Agar ye final nahi hota, toh multiple instances create ho sakte the aur data inconsistency ho sakti thi.
       //This builder will keep building the same TaskFilter object.
       private final TaskFilter taskFilter  = new
               TaskFilter();


       //3. build step by step
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
        // 4. Return final object
       public TaskFilter build(){
           return taskFilter;
       }



    }
}

//Builder pattern:
//
//1. Private constructor
//2. Static inner Builder class
//3. Builder has one object
//4. Setter-like methods return this
//5. build() returns final object

//        Private constructor blocks direct creation.
//        Builder creates step by step.
//        build() gives final object.