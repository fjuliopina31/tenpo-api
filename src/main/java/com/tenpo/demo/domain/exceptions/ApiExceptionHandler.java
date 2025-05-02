package com.tenpo.demo.domain.exceptions;

import org.springframework.http.HttpStatus;

public class ApiExceptionHandler extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final  Integer status;
    private final String message;

    public ApiExceptionHandler(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message=message;
    }
    public ApiExceptionHandler(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = "Error";
    }

    public ApiExceptionHandler(HttpStatus httpStatus, String message, Throwable cause) {
        super(cause);
        this.status = httpStatus.value();
        this.message = message;

    }
    public ApiExceptionHandler(String message) {
        this.status = null;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

}
