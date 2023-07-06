package com.kata.bank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private String firstName;
    private String lastName;

    public Client(String firstName, String lastName){

        this.firstName = firstName;
        this.lastName = lastName;
    }
    
}
