package com.czerwo.reworktracking.ftrot.roles.engineer;

public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private double duration;
    private double status;
    private String workPackageName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getWorkPackageName() {
        return workPackageName;
    }

    public void setWorkPackageName(String workPackageName) {
        this.workPackageName = workPackageName;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public double getStatus() {
        return status;
    }
}
