package com.kata.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.bank.exception.InsufficientSoldException;
import com.kata.bank.exception.RessourceNotFound;
import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;
import com.kata.bank.model.Transaction;

import lombok.Data;

@Data
@Service
public class OperationServiceImp implements OperationService {


    private Transaction transaction;

    public OperationServiceImp(Transaction transaction){

        this.transaction = transaction;

    }

    public OperationServiceImp(){
        
    }

    @Override
    public void withdraw(Account account){

        if(account.getSolde()<this.transaction.getAmount())
            throw new InsufficientSoldException("You cannot withdraw "+this.transaction.getAmount()+" sold insufficient");
        else
            account.setSolde(account.getSolde()-this.transaction.getAmount());

        account.getTransactions().add(this.transaction);

        
    }

    @Override
    public void save(Account account) throws RuntimeException{

        account.setSolde(account.getSolde()+this.transaction.getAmount());
        account.getTransactions().add(this.transaction);

        
    }

    @Override
    public long consult(Account account) {
        
        account.getTransactions().add(this.transaction);
        return account.getSolde();
    }

    @Override
    public List<Transaction> showHistory(Account account) {
        
        account.getTransactions().add(this.transaction);
        return account.getTransactions();
        
    }


    public String toString(){

        return this.transaction.getOp().toString()+" amout : "+this.transaction.getAmount()+" Date : "+this.transaction.getDate();
    }

    @Override
    public Account getAccount(Bank bank, long id) {
        if(bank == null) throw new RessourceNotFound("The bank should not be null");
        Account account = bank.getAccountList().stream().filter(a -> a.getNumber() == id).findFirst().orElseThrow(()-> new RessourceNotFound("The account :"+id+ "does not exist"));

        return account;
    }

    @Override
    public List<Account> getAllAccount(Bank bank) {

        if(bank!= null){
            return bank.getAccountList();
        }else{

            throw new RessourceNotFound("The bank should not be null");
        }

        
    }
    
}
