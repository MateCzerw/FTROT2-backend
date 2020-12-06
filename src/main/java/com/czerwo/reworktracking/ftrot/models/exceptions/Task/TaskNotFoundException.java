package com.czerwo.reworktracking.ftrot.models.exceptions.Task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task with id:" + taskId + " does not exist!");
    }
}
