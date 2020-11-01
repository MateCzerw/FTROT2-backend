package com.czerwo.reworktracking.ftrot.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/test")
    public String test(){
        return "metoda test w controllerze";
    }


}
