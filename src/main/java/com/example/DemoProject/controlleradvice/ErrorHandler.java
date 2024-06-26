package com.example.DemoProject.controlleradvice;

import com.example.DemoProject.dto.ErrorObject;
import com.example.DemoProject.exception.InvalidRoleException;
import com.example.DemoProject.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorObject> Exception3(UnauthorizedException e){
        ErrorObject er = ErrorObject.builder().offsetDateTime(OffsetDateTime.now()).message("Role Mismatch").code(e.getCode()).build();
        return new ResponseEntity<>(er , HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorObject> handleInvalidRoleException(InvalidRoleException ex) {
        ErrorObject er = ErrorObject.builder().offsetDateTime(OffsetDateTime.now()).message("Invalid Role").code(ex.getCode()).build();
        return new ResponseEntity<>(er , HttpStatus.BAD_REQUEST);
    }



}
