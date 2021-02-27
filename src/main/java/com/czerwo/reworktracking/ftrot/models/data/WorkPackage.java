package com.czerwo.reworktracking.ftrot.models.data;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;

import javax.persistence.*;
import java.time.LocalDate;
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

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate deadline;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "workPackage", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

    private boolean isFinished;

    private double status;

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
        //todo copy of set and tasks -> https://www.udemy.com/course/architektura-java/learn/lecture/21652300#overview

        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }



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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
}