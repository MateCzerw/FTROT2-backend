package com.czerwo.ftrot.roles.engineer;

import java.time.LocalDate;

class UserInfoDto {

    private String name;
    private String surname;
    private String team;
    private String role;
    private double reworkRatio;
    private int unfinishedTasks;
    private String supervisor;
    private LocalDate joinedAt;
    private String pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getReworkRatio() {
        return reworkRatio;
    }

    public void setReworkRatio(double reworkRatio) {
        this.reworkRatio = reworkRatio;
    }

    public int getUnfinishedTasks() {
        return unfinishedTasks;
    }

    public void setUnfinishedTasks(int unfinishedTasks) {
        this.unfinishedTasks = unfinishedTasks;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
