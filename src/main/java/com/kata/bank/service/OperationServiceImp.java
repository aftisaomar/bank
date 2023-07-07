package com.kata.bank.service;

import java.util.Date;
import java.util.List;

import com.kata.bank.model.Account;
import com.kata.bank.model.Operation;
import com.kata.bank.model.Transaction;

import lombok.Data;

@Data
public class OperationServiceImp implements OperationService {

    private Transaction transaction;


    public OperationServiceImp(Transaction transaction){

        this.transaction = transaction;

    }

    @Override
    public void withdraw(Account account) throws RuntimeException {

        if(account.getSolde()<this.transaction.getAmount())
            throw new RuntimeException("You cannot withdraw "+this.transaction.getAmount()+" sold insufficient");
        else
            account.setSolde(account.getSolde()-this.transaction.getAmount());

        
    }

    @Override
    public void save(Account account) throws RuntimeException{

        account.setSolde(account.getSolde()+this.transaction.getAmount());

        
    }

    @Override
    public long consult(Account account) {
       
        return account.getSolde();
    }

    @Override
    public List<OperationService> showHistory(Account account) {

        return account.getTransactions();
        
    }


    public String toString(){

        return this.transaction.getOp().toString()+" amout : "+this.transaction.getAmount()+" Date : "+this.transaction.getDate();
    }
    
}
