package com.demo.chatdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String message;
    private String apiPath;
    private HttpStatus errorCode;
    private LocalDateTime errorTime;
}
