package com.czerwo.reworktracking.ftrot.models.dtos;


import java.time.LocalDateTime;

public class WorkPackageDto {

    private long id;

    private String owner;

    private String leadEngineer;

    private String teamLeader;

    private String name;

    private String description;

    private LocalDateTime start;

    private LocalDateTime end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLeadEngineer() {
        return leadEngineer;
    }

    public void setLeadEngineer(String leadEngineer) {
        this.leadEngineer = leadEngineer;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
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
