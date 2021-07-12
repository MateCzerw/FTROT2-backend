package com.czerwo.ftrot.roles.leadEngineer;


import com.czerwo.ftrot.models.data.Task;

class TaskMapper {

    static TaskDto toDto(Task task){
        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setDuration(task.getDuration());
        dto.setName(task.getName());

        return dto;
    }


}
