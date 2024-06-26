package com.example.DemoProject.exception;

import lombok.Data;

import java.nio.file.AccessDeniedException;

@Data
public class UnauthorizedException extends RuntimeException {
    private String code;


    public UnauthorizedException(String code ,String message) {
        super(message);
        this.code = code;
    }
}
