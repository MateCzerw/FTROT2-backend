package com.czerwo.reworktracking.ftrot.models.data.Task;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double durationOfTask;

    private String description;

    @ManyToOne
    @JoinColumn(name="work_package_id")
    private WorkPackage workPackage;

    private LocalDateTime start;

    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private ApplicationUser assignedEngineer;

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

    public WorkPackage getWorkPackage() {
        return workPackage;
    }

    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ApplicationUser getAssignedEngineer() {
        return assignedEngineer;
    }

    public void setAssignedEngineer(ApplicationUser assignedEngineer) {
        this.assignedEngineer = assignedEngineer;
    }
}
