package com.tenpo.demo.infrastructure.rest.controller;

import com.tenpo.demo.domain.exceptions.ApiExceptionHandler;
import com.tenpo.demo.infrastructure.rest.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TenpoCalculateControllerAdvice {

    @ExceptionHandler(ApiExceptionHandler.class)
    public ResponseEntity<ExceptionDTO> exception(ApiExceptionHandler ex) {
        ExceptionDTO exception = new ExceptionDTO();
        exception.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        exception.setMessage(ex.getMessage());
        exception.setType(HttpStatus.NOT_FOUND.getReasonPhrase());
        exception.setSchemaLocation("API TENPO");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }
}
