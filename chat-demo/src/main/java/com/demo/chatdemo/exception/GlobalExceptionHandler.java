package com.demo.chatdemo.exception;


import com.demo.chatdemo.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponseDto handleResourceNotFoundException(ResourceNotFoundException ex,  WebRequest webRequest){
        return ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

}
