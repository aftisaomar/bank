package com.kata.bank.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;
import com.kata.bank.model.Client;

import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "*")
@RequestMapping("/bank")
@RestController
public class OperationController {

    private Bank bank;

    @PostConstruct
	public void bankInit()
	{   
        List<Account> accountList = new ArrayList<>();

        Account c1 = new Account(001, new Client("dustin", "poirier"));
        Account c2 = new Account(002,new Client("Mohamed", "Ali"));
        Account c3 = new Account(003, new Client("Nate", "Diaz"));

        accountList.add(c1);
        accountList.add(c2);
        accountList.add(c3);

		this.bank = new Bank("LCL", accountList);
	}

    @GetMapping("/bank")
    public ResponseEntity<Bank> getBank(){

        return new ResponseEntity<>(this.bank, HttpStatus.OK);

    }




    
}
