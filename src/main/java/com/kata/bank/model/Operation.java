package com.kata.bank.model;

import lombok.Getter;

@Getter
public enum Operation {

    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    CONSULT("consult"),
    HISTORY("history");

    public final String label;

    private Operation(String label){

        this.label = label;
    }


    public String toString(){

        return this.label;
    }
    
}
