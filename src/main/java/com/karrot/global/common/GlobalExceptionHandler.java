package com.karrot.global.common;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotYetImplementedException.class)
    public ResponseEntity<String> handleNotYetImplementedException(NotYetImplementedException ex) {
        return new ResponseEntity<>("Not yet implemented", HttpStatus.NOT_IMPLEMENTED);
    }
}