package com.czerwo.reworktracking.ftrot.models.data.Task;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;

import javax.persistence.*;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double duration;

    private String description;

    @ManyToOne
    private WorkPackage workPackage;

    @ManyToOne
    private Day day;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private ApplicationUser assignedEngineer;

    private boolean isInBacklog;

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

    public boolean isInBacklog() {
        return isInBacklog;
    }

    public void setInBacklog(boolean inBacklog) {
        isInBacklog = inBacklog;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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
