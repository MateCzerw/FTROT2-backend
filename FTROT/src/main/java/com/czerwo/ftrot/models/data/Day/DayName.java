package com.czerwo.ftrot.models.data.Day;


import java.util.Calendar;

public enum DayName {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY;

    public static int getCalendarDay(DayName dayName) {
        switch(dayName){
            case MONDAY: {return Calendar.MONDAY;}
            case TUESDAY: {return Calendar.TUESDAY;}
            case WEDNESDAY: {return Calendar.WEDNESDAY;}
            case THURSDAY: {return Calendar.THURSDAY;}
            default: return Calendar.FRIDAY;
        }
    }
}