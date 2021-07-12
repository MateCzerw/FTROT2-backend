package com.czerwo.ftrot.models.exceptions.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Week does not exist")
public class WrongDateException extends RuntimeException {
}
