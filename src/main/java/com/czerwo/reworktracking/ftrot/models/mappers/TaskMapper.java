package com.czerwo.reworktracking.ftrot.models.mappers;

import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public TaskDto toDto(Task task){

        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStatus(task.getStatus());
        dto.setDuration(task.getDuration());
        dto.setDescription(task.getDescription());

        return  dto;
    }

//    public Task toEntity(TaskDto task){
//        Task entity = new Task();
//
////        entity.setId(task.getId());
////        entity.setName(task.getName());
////        entity.setDescription(task.getDescription());
////        entity.setDurationOfTask(task.getDurationOfTask());
////        //entity.setStatus(Status.valueOf(task.getStatus()));
////        entity.setStart(task.getStart());
//
//    }

}
