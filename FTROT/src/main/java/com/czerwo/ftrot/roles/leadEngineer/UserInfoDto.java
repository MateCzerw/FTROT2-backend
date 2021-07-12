package com.czerwo.ftrot.roles.leadEngineer;

import java.time.LocalDate;

class UserInfoDto {

    private String name;
    private String surname;
    private String team;
    private String role;
    private int finishedWorkPackages;
    private int unfinishedWorkPackages;
    private String supervisor;
    private LocalDate joinedAt;
    private String pictureUrl;


    public UserInfoDto() {
    }

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

    public int getFinishedWorkPackages() {
        return finishedWorkPackages;
    }

    public void setFinishedWorkPackages(int finishedWorkPackages) {
        this.finishedWorkPackages = finishedWorkPackages;
    }

    public int getUnfinishedWorkPackages() {
        return unfinishedWorkPackages;
    }

    public void setUnfinishedWorkPackages(int unfinishedWorkPackages) {
        this.unfinishedWorkPackages = unfinishedWorkPackages;
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
