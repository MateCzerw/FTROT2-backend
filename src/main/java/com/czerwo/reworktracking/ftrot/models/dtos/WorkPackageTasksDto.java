package com.czerwo.reworktracking.ftrot.models.dtos;


import java.time.LocalDate;
import java.util.List;

public class WorkPackageTasksDto {

    private long id;

    private String name;

    private int taskQuantity;

    private int taskFinished;

    private String description;

    private LocalDate startDate;

    private LocalDate deadline;

    private LocalDate predictedFinish;

    private List<TaskDto> taskDtos;

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

    public int getTaskQuantity() {
        return taskQuantity;
    }

    public void setTaskQuantity(int taskQuantity) {
        this.taskQuantity = taskQuantity;
    }

    public int getTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(int taskFinished) {
        this.taskFinished = taskFinished;
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

    public List<TaskDto> getTaskDtos() {
        return taskDtos;
    }

    public void setTaskDtos(List<TaskDto> taskDtos) {
        this.taskDtos = taskDtos;
    }
}
