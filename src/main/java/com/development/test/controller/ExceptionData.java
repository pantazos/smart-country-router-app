package com.development.test.controller;

import com.development.test.exception.RouteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionData extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity handleRouteNotFoundException(RouteNotFoundException ex) {
        return new ResponseEntity("ROUTE NOT FOUND!", BAD_REQUEST);
    }
}
