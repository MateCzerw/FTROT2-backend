package com.czerwo.ftrot.models.exceptions.Day;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Day not found")
public class DayNotFoundException extends RuntimeException {
}
