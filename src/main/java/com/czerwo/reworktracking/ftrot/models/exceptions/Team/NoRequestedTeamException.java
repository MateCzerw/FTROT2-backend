package com.czerwo.reworktracking.ftrot.models.exceptions.Team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is not team with this member")
public class NoRequestedTeamException extends RuntimeException {
}
