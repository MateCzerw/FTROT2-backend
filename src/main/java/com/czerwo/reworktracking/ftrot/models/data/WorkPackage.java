package com.czerwo.reworktracking.ftrot.models.data;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.Task.Task;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private ApplicationUser owner;

    @OneToOne
    private ApplicationUser assignedLeadEngineer;

    @OneToOne
    private ApplicationUser assignedTeamLeader;

    private String name;

    private String description;

    private LocalDateTime start;

    private LocalDateTime end;


    @OneToMany(mappedBy = "workPackage")
    private List<Task> tasks = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public ApplicationUser getAssignedLeadEngineer() {
        return assignedLeadEngineer;
    }

    public void setAssignedLeadEngineer(ApplicationUser assignedLeadEngineer) {
        this.assignedLeadEngineer = assignedLeadEngineer;
    }

    public ApplicationUser getAssignedTeamLeader() {
        return assignedTeamLeader;
    }

    public void setAssignedTeamLeader(ApplicationUser assignedTeamLeader) {
        this.assignedTeamLeader = assignedTeamLeader;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
}




