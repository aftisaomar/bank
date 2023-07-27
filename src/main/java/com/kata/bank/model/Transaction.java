package com.kata.bank.model;

import java.util.Date;

import lombok.Data;

@Data
public class Transaction {


    private Operation op;
    private long amount;
    private Date date;

    public Transaction(Operation op, long amount, Date date){

        this.op=op;
        this.amount=amount;
        this.date=date;
    }

    public Transaction(Operation op, Date date){

        this.op = op;
        this.date = date;


    }



    public String toString(){

        return "Operation : "+op+" amout : "+amount+" Date : "+date;
    }
    
}
