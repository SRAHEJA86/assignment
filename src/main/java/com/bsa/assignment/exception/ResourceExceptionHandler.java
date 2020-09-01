package com.bsa.assignment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PeopleNotFoundException.class)
    public void handlePeopleNotFound(PeopleNotFoundException e){
        log.error("The person requested could not be found");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SkillsNotFoundException.class)
    public void handlePeopleNotFound(SkillsNotFoundException e){
        log.error("No skills recorded for the person");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        log.error("An error occurred while processing request" + ex);
    }
}
