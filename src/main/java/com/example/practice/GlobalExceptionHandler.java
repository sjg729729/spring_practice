package com.example.practice;

import com.example.practice.web.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * IllegalArgumentException 처리
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDto> handleIllegalArgumentException(Exception ex, HttpServletRequest request) {
        ApiErrorDto apiErrorDto = ApiErrorDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(apiErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * IllegalStateException 처리
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorDto> handleIllegalStateException(Exception ex, HttpServletRequest request) {
        ApiErrorDto apiErrorDto = ApiErrorDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(400)
                .error("IllegalStateException 이지롱")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(apiErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

