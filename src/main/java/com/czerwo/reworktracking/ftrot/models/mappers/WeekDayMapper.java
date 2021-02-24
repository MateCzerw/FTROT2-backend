package com.czerwo.reworktracking.ftrot.models.mappers;

import com.czerwo.reworktracking.ftrot.models.data.Week;
import com.czerwo.reworktracking.ftrot.models.dtos.DayDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WeekDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekDayMapper {

    public WeekDto toDto(Week week, List<DayDto> dayDtos){
        WeekDto dto = new WeekDto();

        dto.setId(week.getId());
        dto.setWeekNumber(week.getWeekNumber());
        dto.setYearNumber(week.getYearNumber());
        dto.setDays(dayDtos);

        return dto;
    }



}
