package com.kata.bank.service;

import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;
import com.kata.bank.model.Transaction;

import java.util.List;
public interface OperationService {

    public void withdraw(Account account);
    public void save(Account account);
    public long consult(Account account);
    public List<Transaction> showHistory(Account account);
    public Account getAccount(Bank bank, long id);
    public List<Account> getAllAccount(Bank bank);


    
}
