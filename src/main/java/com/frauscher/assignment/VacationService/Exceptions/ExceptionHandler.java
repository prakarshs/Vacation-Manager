package com.frauscher.assignment.VacationService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorBody> customError(NotExistException exception){
        return new ResponseEntity<>(ErrorBody.builder()
                .message(exception.getMessage())
                .resolution(exception.getResolution())
                .build(), HttpStatus.NOT_FOUND);
    }
}
