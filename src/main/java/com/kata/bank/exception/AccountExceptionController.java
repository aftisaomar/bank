package com.kata.bank.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionController {


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage accountNotFound(RessourceNotFound ex){

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.NOT_FOUND.value());
        message.setTimestamp(new Date());
        message.setDescription("Account not Found");

        return message;

    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionMessage insufficientSold (InsufficientSoldException ex){

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.CONFLICT.value());
        message.setTimestamp(new Date());
        message.setDescription("Cannot withdraw");

        return message;


    }


    
}
