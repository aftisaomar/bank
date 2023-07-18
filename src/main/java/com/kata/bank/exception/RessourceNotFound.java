package com.kata.bank.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RessourceNotFound extends RuntimeException {


    private String message;

    public RessourceNotFound(String message){
        this.message = message;
    }    
}
