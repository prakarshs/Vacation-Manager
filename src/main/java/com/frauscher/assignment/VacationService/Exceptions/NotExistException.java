package com.frauscher.assignment.VacationService.Exceptions;

import lombok.Data;

@Data
public class NotExistException extends RuntimeException{
    private String resolution;
    public NotExistException(String message, String resolution){
        super(message);
        this.resolution = resolution;
    }
}
