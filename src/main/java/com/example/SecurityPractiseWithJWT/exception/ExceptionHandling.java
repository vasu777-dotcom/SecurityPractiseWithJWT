package com.example.SecurityPractiseWithJWT.exception;

import com.example.SecurityPractiseWithJWT.entity.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<ErrorDto>> handleException(Exception exception){
        log.error("Error code: {} thrown from controller for Exception ::: {}",500,exception.getMessage());
        return errorResolver(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    public ResponseEntity<ResponseDto<ErrorDto>> errorResolver(HttpStatus httpStatus, String errorMessage){
        ErrorDto error = new ErrorDto();
        error.setCode(httpStatus.value());
        error.setMessage(errorMessage);
        ResponseDto<ErrorDto> dto = new ResponseDto<>();
        dto.setStatus("ERROR");
        dto.setErrors(List.of(error));
        return new ResponseEntity<>(dto, httpStatus);
    }
}
