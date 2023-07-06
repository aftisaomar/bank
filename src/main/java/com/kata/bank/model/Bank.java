package com.kata.bank.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bank {


    private String name;

    private List<Account> accountList;

    public Bank(String name, List<Account> accountList){

        this.name = name;
        this.accountList = accountList;
    }
    
}
