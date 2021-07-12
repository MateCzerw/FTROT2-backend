package com.czerwo.ftrot.models.mappers;

import com.czerwo.ftrot.models.data.Week;
import com.czerwo.ftrot.models.dtos.DayDto;
import com.czerwo.ftrot.models.dtos.WeekDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeekDayMapper {

    public WeekDto toDto(Week week, List<DayDto> dayDtos){
        WeekDto dto = new WeekDto();

        dto.setId(week.getId());
        dto.setWeekNumber(week.getWeekNumber());
        dto.setYearNumber(week.getYearNumber());
        dto.setDays(dayDtos
                .stream()
                .sorted(Comparator
                        .comparing(DayDto::getDate,(date1,date2) -> {return date1.isAfter(date2) ? 1:-1;}))
                .collect(Collectors.toList()));

        return dto;
    }



}
