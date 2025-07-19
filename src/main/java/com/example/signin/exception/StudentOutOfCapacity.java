package com.example.signin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = "All the presents have been distributed")
public class StudentOutOfCapacity extends RuntimeException{
    public StudentOutOfCapacity(String message){
        super(message);
    }
}
