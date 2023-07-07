package com.kata.bank.service;

import com.kata.bank.model.Account;
import java.util.List;
public interface Transaction {

    public void withdraw(Account account);
    public void save(Account account);
    public long consult(Account account);
    public List<Transaction> showHistory(Account account);


    
}
