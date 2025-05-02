package com.tenpo.demo.infrastructure.rest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExceptionDTO implements Serializable {

    private String type;
    private String baseType;
    private String SchemaLocation;
    private String code;
    private String message;

    public ExceptionDTO(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getSchemaLocation() {
        return SchemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        SchemaLocation = schemaLocation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
