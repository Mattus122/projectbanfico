package com.example.DemoProject.exception;

import lombok.Data;

@Data
public class StudentNotFoundException extends RuntimeException{
    private String code;

    public StudentNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public StudentNotFoundException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public StudentNotFoundException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }
}
