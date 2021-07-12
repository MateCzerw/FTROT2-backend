package com.czerwo.ftrot.models.data;

import com.czerwo.ftrot.auth.ApplicationUser;
import com.czerwo.ftrot.models.data.Day.Day;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApplicationUser user;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    private Set<Day> days = new HashSet<>();

    private int weekNumber;
    private int yearNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Set<Day> getDays() {
        return days;
    }

    public void addDayToWeek(Day day){
        days.add(day);
    }

    public void removeDayFromWeek(Day day) {
        days.remove(day);
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


}