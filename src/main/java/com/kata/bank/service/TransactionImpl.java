package com.kata.bank.service;

import java.util.Date;
import java.util.List;

import com.kata.bank.model.Account;
import com.kata.bank.model.Operation;

public class TransactionImpl implements Transaction {

    private Operation op;
    private long amount;
    private Date date;


    public TransactionImpl(Operation op, long amount, Date date){

        this.op = op;
        this.amount = Math.abs(amount);
        this.date = date;

    }

    @Override
    public void Withdraw(Account account) throws RuntimeException {

        if(account.getSolde()<amount)
            throw new RuntimeException("You cannot withdraw "+amount+" sold insufficient");
        else
            account.setSolde(account.getSolde()-amount);

        
    }

    @Override
    public void save(Account account) throws RuntimeException{

        account.setSolde(account.getSolde()+amount);

        
    }

    @Override
    public long consult(Account account) {
       
        return account.getSolde();
    }

    @Override
    public List<TransactionImpl> showHistory(Account account) {

        return account.getTransactions();
        
    }


    public String toString(){

        return op.toString()+" amout : "+amount+" Date : "+date;
    }
    
}
