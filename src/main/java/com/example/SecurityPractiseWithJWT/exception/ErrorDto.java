package com.example.SecurityPractiseWithJWT.exception;

import lombok.Data;

@Data
public class ErrorDto {
    private Integer code;
    private String message;
}
