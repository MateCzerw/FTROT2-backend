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


    public int getNextWeekNumber(int week, int year){


        int lastWeekInTheYear;

        if(week + 1 > lastWeekInTheYear) return 1;

        else return week+1;

    }

    public int getNextWeekYear(int week, int year){

        return 1;
    }

    public int getPreviousWeekNumber(int week, int year){

        return 1;
    }

    public int
    getPreviousWeekYear(int week, int year){

        return 1;
    }


}
