package com.example.SecurityPractiseWithJWT.entity.model;

import com.example.SecurityPractiseWithJWT.exception.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String status;
    private T data;
    private List<ErrorDto> errors;
}
