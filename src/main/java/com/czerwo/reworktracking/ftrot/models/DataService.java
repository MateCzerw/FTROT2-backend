package com.czerwo.reworktracking.ftrot.models;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

@Service
public class DataService {




    public LocalDate getClosestWorkingDay(){
        LocalDate date = LocalDate.now();
        if(date.getDayOfWeek().name().equals(DayOfWeek.SUNDAY.name())) return date.minusDays(2);
        if(date.getDayOfWeek().name().equals(DayOfWeek.SATURDAY.name())) return date.minusDays(1);
        return date;
    }

    public int getWeeksInWeekYear(int yearNumber) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNumber);
        return cal.getWeeksInWeekYear();
    }

    public int getCurrentWeekNumber(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public int getCurrentYearNumber(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));
        return cal.get(Calendar.YEAR);
    }


    public int getNextWeekNumber(int currentWeek, int currentYear){

        if(currentWeek + 1 > getWeeksInWeekYear(currentYear)) return 1;
        else return currentWeek+1;
    }



    public int getNextWeekYear(int currentWeek, int currentYear){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, currentYear);

        if(currentWeek + 1 > getWeeksInWeekYear(currentYear)) return currentYear + 1;
        else return currentYear;
    }

    public int getPreviousWeekNumber(int currentWeek, int currentYear){

        if(currentWeek - 1 < 1) return getWeeksInWeekYear(currentYear-1);
        return currentWeek - 1;
    }

    public int getPreviousWeekYear(int currentWeek, int currentYear){

        if(currentWeek - 1 < 1) return currentYear - 1;
        return currentYear;
    }




}
