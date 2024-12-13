package com.codingapple.javatest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 2. 예외를 감지함
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생");
    }

    // 1. 존재하지 않는 id로 접근시 RuntimeException 발생
    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        // 3. 예외를 처리하여 error.html반환
        return "error.html";
    }
}
