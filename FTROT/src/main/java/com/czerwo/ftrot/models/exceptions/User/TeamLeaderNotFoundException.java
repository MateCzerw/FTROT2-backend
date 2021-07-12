package com.czerwo.ftrot.models.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Team leader not found")
public class TeamLeaderNotFoundException extends RuntimeException {
}
