package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WeekDto;

import java.util.List;

public class EngineerDto {

    private long id;
    private String firstName;
    private String lastName;
    private String picture;
    private WeekDto week;
    private List<TaskDto> backlog;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public WeekDto getWeek() {
        return week;
    }

    public void setWeek(WeekDto week) {
        this.week = week;
    }

    public List<TaskDto> getBacklog() {
        return backlog;
    }

    public void setBacklog(List<TaskDto> backlog) {
        this.backlog = backlog;
    }
}
