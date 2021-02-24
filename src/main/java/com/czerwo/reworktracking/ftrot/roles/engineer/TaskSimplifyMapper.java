package com.czerwo.reworktracking.ftrot.roles.engineer;

import com.czerwo.reworktracking.ftrot.models.data.Task;

public class TaskSimplifyMapper {
    static TaskSimplifyDto toDto(Task task){
        TaskSimplifyDto dto = new TaskSimplifyDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setEstimatedTime(task.getDuration());
        dto.setStatus(task.getStatus());

        return dto;
    }
}
