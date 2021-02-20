package com.czerwo.reworktracking.ftrot.roles.engineer;

import com.czerwo.reworktracking.ftrot.roles.engineer.DayDto;

import java.util.LinkedList;
import java.util.List;

public class WeekDto {

    private Long id;
    private int weekNumber;
    private int yearNumber;
    private List<DayDto> days;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public void addDayDto(DayDto dayDto) {
        days.add(dayDto);
    }

    public void removeDayDto(DayDto dayDto) {
        days.remove(dayDto);
    }

    public List<DayDto> getDays() {
        return days;
    }

    public void setDays(List<DayDto> days) {
        this.days = days;
    }
}
