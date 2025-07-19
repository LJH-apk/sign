package com.example.signin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRespurceNotFound(ResourceNotFoundException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error",ex.getMessage());
        errorResponse.put("timestampe", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //处理抽取礼物时学生数量超过礼物的问题
    @ExceptionHandler(StudentOutOfCapacity.class)
    public ResponseEntity<Map<String,String>> handleStudentOutOfCapacity(StudentOutOfCapacity ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("success","false");
        errorResponse.put("message",ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED) //501错误
                .body(errorResponse);
    }


}
