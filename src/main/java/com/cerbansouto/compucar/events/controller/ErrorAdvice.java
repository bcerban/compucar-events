package com.cerbansouto.compucar.events.controller;

import com.cerbansouto.compucar.events.api.EntityNotFoundException;
import com.cerbansouto.compucar.events.api.ErrorResponse;
import com.cerbansouto.compucar.events.api.InvalidEventException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse noSuchEntityHandler(EntityNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidEventException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse invalidEventHandler(InvalidEventException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse unexpectedErrorHandler(Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
