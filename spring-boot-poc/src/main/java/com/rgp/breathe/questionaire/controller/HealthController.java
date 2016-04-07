package com.rgp.breathe.questionaire.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${app.name}")
    private String appName;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot, I am perfectly fine!";
    }

    @RequestMapping("/app-name")
    public String getAppName() {
        return appName;
    }
    
}
