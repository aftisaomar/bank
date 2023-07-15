package com.kata.bank.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountNotFoundException extends RuntimeException {


    private String message;

    public AccountNotFoundException(String message){
        this.message = message;
    }    
}
