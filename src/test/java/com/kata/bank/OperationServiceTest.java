package com.kata.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kata.bank.model.Account;
import com.kata.bank.model.Transaction;
import com.kata.bank.service.OperationServiceImp;


@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @Mock
    private Transaction transaction;
    private Account account;
    private OperationServiceImp op;
    

    @BeforeEach
    void setUp(){

        System.out.println("the methode setUp is executed");

        this.op = new OperationServiceImp(transaction);
        this.account = new Account(0, null);
        this.account.setSolde(150);
    }


    @Test
    void withdrawTest(){

        when(this.transaction.getAmount()).thenReturn(100L);
        System.out.println(this.account.getSolde());
        this.op.withdraw(this.account);
        assertEquals(50, this.account.getSolde());

    }

    @Test
    void saveTest(){
        when(this.transaction.getAmount()).thenReturn(100L);
        this.op.save(account);
        assertEquals(250, this.account.getSolde());
    }




    
}
