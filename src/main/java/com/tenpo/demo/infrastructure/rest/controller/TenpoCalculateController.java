package com.tenpo.demo.infrastructure.rest.controller;

import com.tenpo.demo.application.usecase.ICalculateUseCase;
import com.tenpo.demo.domain.Calculate;
import com.tenpo.demo.infrastructure.rest.dto.CalculateDTO;
import com.tenpo.demo.infrastructure.utilities.CalculateMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TenpoCalculateController {

    @Autowired
    private ICalculateUseCase calculateUseCase;

    @Autowired
    private CalculateMapper calculateMapper;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@Valid @RequestBody CalculateDTO calculateDTO) {

        Calculate calculate = calculateMapper.toDomain(calculateDTO);
        return new ResponseEntity<>(calculateUseCase.calculatePercentage(calculate).toString(), HttpStatus.OK);
    }
}
