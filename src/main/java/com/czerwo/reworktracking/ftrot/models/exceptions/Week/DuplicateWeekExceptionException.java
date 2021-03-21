package com.czerwo.reworktracking.ftrot.models.exceptions.Week;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Current week already exists")
public class DuplicateWeekExceptionException extends RuntimeException {
}
