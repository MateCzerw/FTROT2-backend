package com.czerwo.ftrot.models.mappers;

import com.czerwo.ftrot.models.data.Day.Day;
import com.czerwo.ftrot.models.dtos.DayDto;
import com.czerwo.ftrot.models.dtos.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayTasksMapper {

    public DayDto toDto(Day day, List<TaskDto> taskDtos){

        DayDto dto = new DayDto();

        dto.setId(day.getId());
        dto.setDate(day.getDate());
        dto.setDayName(day.getDayName().name());
        dto.setTasks(taskDtos);

        return  dto;
    }

}
