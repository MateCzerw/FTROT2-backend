package com.czerwo.ftrot.models.exceptions.WorkPackage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WorkPackageNotFoundException extends RuntimeException {
    public WorkPackageNotFoundException(Long workPackageId) {
        super("Work Package with id: " + workPackageId + " does not exist.");
    }
}
