package com.kata.bank.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientSoldException extends RuntimeException{

    private String message;

    public InsufficientSoldException(String message){
        this.message = message;
    }
    
}
