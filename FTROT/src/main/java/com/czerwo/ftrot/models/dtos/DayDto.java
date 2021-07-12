package com.czerwo.ftrot.models.dtos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DayDto {

    private Long id;
    private String dayName;
    private LocalDate date;
    private List<TaskDto> tasks = new LinkedList<>() ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
