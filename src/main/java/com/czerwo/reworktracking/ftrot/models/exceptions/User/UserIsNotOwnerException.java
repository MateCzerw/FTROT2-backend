package com.czerwo.reworktracking.ftrot.models.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User is not an owner of work package")
public class UserIsNotOwnerException extends RuntimeException {
}
