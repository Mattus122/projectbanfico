package com.example.DemoProject.exception;

import lombok.Data;

import java.lang.reflect.InaccessibleObjectException;

@Data
public class InvalidRoleException extends RuntimeException{

    private String code;
    public InvalidRoleException(){}
    public InvalidRoleException(String code , String message){
        super(message);
        this.code=code;

    }
}
