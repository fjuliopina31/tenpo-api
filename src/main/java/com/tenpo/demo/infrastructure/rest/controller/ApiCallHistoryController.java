package com.tenpo.demo.infrastructure.rest.controller;

import com.tenpo.demo.application.service.IApiCallHistoryService;
import com.tenpo.demo.domain.ApiCallHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiCallHistoryController {

    private final IApiCallHistoryService apiCallHistoryService;

    public ApiCallHistoryController(IApiCallHistoryService apiCallHistoryService) {
        this.apiCallHistoryService = apiCallHistoryService;
    }

    @GetMapping("/call-history")
    public ResponseEntity<List<ApiCallHistory>> getApiCallHistory(@RequestParam(value = "id", required = false) Long id) {
        return new ResponseEntity<>(apiCallHistoryService.getAllApiCallHistory(), HttpStatus.OK);
    }
}
