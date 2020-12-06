package com.czerwo.reworktracking.ftrot.models.dtos;

import java.time.LocalDateTime;

public class TaskDto {

    private long id;

    private String name;

    private double durationOfTask;

    private String description;

    private LocalDateTime start;

    private LocalDateTime end;

    private String status;

    private String assignedEngineer;

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

    public double getDurationOfTask() {
        return durationOfTask;
    }

    public void setDurationOfTask(double durationOfTask) {
        this.durationOfTask = durationOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedEngineer() {
        return assignedEngineer;
    }

    public void setAssignedEngineer(String assignedEngineer) {
        this.assignedEngineer = assignedEngineer;
    }
}
