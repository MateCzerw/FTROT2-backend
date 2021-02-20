package com.czerwo.reworktracking.ftrot.models.data;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WorkPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private ApplicationUser assignedTechnicalProjectManager;

    @OneToOne
    private ApplicationUser assignedLeadEngineer;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "workPackage")
    private Set<Task> tasks = new HashSet<>();

    public ApplicationUser getAssignedTechnicalProjectManager() {
        return assignedTechnicalProjectManager;
    }

    public void setAssignedTechnicalProjectManager(ApplicationUser assignedTechnicalProjectManager) {
        this.assignedTechnicalProjectManager = assignedTechnicalProjectManager;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime deadline;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public ApplicationUser getAssignedLeadEngineer() {
        return assignedLeadEngineer;
    }

    public void setAssignedLeadEngineer(ApplicationUser assignedLeadEngineer) {
        this.assignedLeadEngineer = assignedLeadEngineer;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}