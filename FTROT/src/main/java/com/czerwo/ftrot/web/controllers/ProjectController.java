package com.czerwo.ftrot.web.controllers;

import com.czerwo.ftrot.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class ProjectController {

    private final TestService testService;

    @GetMapping
    public void getCredits() {

        testService.createMessage();

    }
}
