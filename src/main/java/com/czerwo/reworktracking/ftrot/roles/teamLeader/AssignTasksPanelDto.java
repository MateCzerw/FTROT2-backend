package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;

import java.util.LinkedList;
import java.util.List;

public class AssignTasksPanelDto {

    List<TaskDto> unassignedTasks = new LinkedList<>();

    List<EngineerDto> engineers = new LinkedList<>();

    public List<TaskDto> getUnassignedTasks() {
        return unassignedTasks;
    }

    public void setUnassignedTasks(List<TaskDto> unassignedTasks) {
        this.unassignedTasks = unassignedTasks;
    }

    public List<EngineerDto> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<EngineerDto> engineers) {
        this.engineers = engineers;
    }
}
