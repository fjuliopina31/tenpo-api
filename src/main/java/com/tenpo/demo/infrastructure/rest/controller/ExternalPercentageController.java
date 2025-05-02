package com.tenpo.demo.infrastructure.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalPercentageController {

    @Value("${external.percentage.value}")
    private double percentage;

    @GetMapping("/external/percentage")
    public Double getPercentage() {
        return percentage;
    }
}
