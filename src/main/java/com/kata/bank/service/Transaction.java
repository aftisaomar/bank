package com.kata.bank.service;

import com.kata.bank.model.Account;
import java.util.List;
public interface Transaction {

    public void Withdraw(Account account);
    public void save(Account account);
    public long consult(Account account);
    public List<TransactionImpl> showHistory(Account account);


    
}
