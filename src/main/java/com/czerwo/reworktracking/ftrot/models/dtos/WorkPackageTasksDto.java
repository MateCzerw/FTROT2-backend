package com.czerwo.reworktracking.ftrot.models.dtos;


import java.time.LocalDate;
import java.util.List;

public class WorkPackageTasksDto {

    private long id;

    private String name;

    private int tasksQuantity;

    private int finishedTasks;

    private String description;

    private LocalDate startDate;

    private LocalDate deadline;

    private LocalDate predictedFinish;

    private List<TaskDto> tasks;

    private String LeadEngineerName;

    private String LeadEngineerSurname;

    private String LeadEngineerUsername;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTasksQuantity() {

        return tasksQuantity;
    }

    public void setTasksQuantity(int tasksQuantity) {
        this.tasksQuantity = tasksQuantity;
    }

    public int getFinishedTasks() {
        return finishedTasks;
    }

    public void setFinishedTasks(int finishedTasks) {
        this.finishedTasks = finishedTasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getPredictedFinish() {
        return predictedFinish;
    }

    public void setPredictedFinish(LocalDate predictedFinish) {
        this.predictedFinish = predictedFinish;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }


    public String getLeadEngineerName() {
        return LeadEngineerName;
    }

    public void setLeadEngineerName(String leadEngineerName) {
        LeadEngineerName = leadEngineerName;
    }

    public String getLeadEngineerSurname() {
        return LeadEngineerSurname;
    }

    public void setLeadEngineerSurname(String leadEngineerSurname) {
        LeadEngineerSurname = leadEngineerSurname;
    }

    public String getLeadEngineerUsername() {
        return LeadEngineerUsername;
    }

    public void setLeadEngineerUsername(String leadEngineerUsername) {
        LeadEngineerUsername = leadEngineerUsername;
    }
}
