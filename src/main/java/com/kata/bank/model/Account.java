package com.kata.bank.model;

import java.util.ArrayList;
import java.util.List;

import com.kata.bank.service.OperationService;
import com.kata.bank.service.OperationServiceImp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    

    private long number;
    private Client client;
    private List<Transaction> transactions;
    private long solde;

    public Account(long number, Client client){

        this.number = number;
        this.client = client;
        this.transactions = new ArrayList<>();
        solde = 0;
    }
}
