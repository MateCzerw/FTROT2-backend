package com.czerwo.ftrot.models.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends  RuntimeException {

    public UserNotFoundException(String message) {
        super("User with name:" + message + " does not exist!");
    }
}
