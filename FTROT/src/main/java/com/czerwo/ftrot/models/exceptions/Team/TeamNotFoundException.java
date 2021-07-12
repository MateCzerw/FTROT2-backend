package com.czerwo.ftrot.models.exceptions.Team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Team not found exception")
public class TeamNotFoundException extends RuntimeException {
}
