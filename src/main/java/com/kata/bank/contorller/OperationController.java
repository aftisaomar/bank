package com.kata.bank.contorller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;
import com.kata.bank.model.Client;
import com.kata.bank.model.Operation;
import com.kata.bank.model.Transaction;
import com.kata.bank.service.OperationServiceImp;

import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "*")
@RequestMapping("/bank")
@RestController
public class OperationController {

    private  Bank bank;

    @Autowired
    private OperationServiceImp operationServiceImp;

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

        if(this.bank!=null){
            return new ResponseEntity<>(this.bank, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccounts(){

        return new ResponseEntity<>(operationServiceImp.getAllAccount(bank), HttpStatus.OK);

    }


    // @GetMapping("/transaction/{id}")
    // public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") Long id){

    //     Account a = bank.getAccountList().stream().filter(account -> account.getNumber() == id).findFirst().orElse(null);

    //     if(a != null) {

    //         return new ResponseEntity<>(a.getTransactions(), HttpStatus.OK);

    //     }else{

    //         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    //     }
        
    // }

    @PostMapping(value = "/transaction/{operation}/{amount}")
    public ResponseEntity<Transaction> perfomTransaction(@RequestBody Account inputAccount, @PathVariable String operation, @PathVariable long amount){

        Operation op = Operation.valueOf(operation);
        
        Account currentAccount = operationServiceImp.getAccount(bank, inputAccount.getNumber());


        Transaction transaction = new Transaction(op, amount, new Date());
        operationServiceImp.setTransaction(transaction);

        switch (op) {
            case DEPOSIT:
                operationServiceImp.save(currentAccount);
                break;
            case WITHDRAW:
                operationServiceImp.withdraw(currentAccount);
            case CONSULT:
                operationServiceImp.consult(currentAccount);
                break;
            case HISTORY:
                operationServiceImp.showHistory(currentAccount);
                break;
            default:
                break;
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }


    @PostMapping("/transaction/save/{amount}")
    public ResponseEntity<Account> saveMoney(@RequestBody Account account, @PathVariable long amount){

        Account currentAccount = operationServiceImp.getAccount(bank, account.getNumber());
        Transaction currentTansaction = new Transaction(Operation.DEPOSIT, amount, new Date());

        operationServiceImp.setTransaction(currentTansaction);
        operationServiceImp.save(currentAccount);

        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);

    }

    @GetMapping("/transaction/get/{amount}")
    public ResponseEntity<Account> getMoney(@RequestBody Account account, @PathVariable long amount) {

        Account currentAccount = operationServiceImp.getAccount(bank, account.getNumber());
        Transaction currentTansaction = new Transaction(Operation.WITHDRAW, amount, new Date());

        operationServiceImp.setTransaction(currentTansaction);
        operationServiceImp.withdraw(currentAccount);

        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);

    }

    @GetMapping("/transaction/consult/{id}")
    public ResponseEntity<Account> consultAccount(@PathVariable long id){


        Account currentAccount = operationServiceImp.getAccount(bank, id);
        Transaction currentTansaction = new Transaction(Operation.CONSULT, 0, new Date());

        operationServiceImp.setTransaction(currentTansaction);
        operationServiceImp.consult(currentAccount);

        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);

    }

    @GetMapping("/transaction/history/{id}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable long id){

        Account currentAccount = operationServiceImp.getAccount(bank, id);
        Transaction currentTansaction = new Transaction(Operation.HISTORY, 0, new Date());

        operationServiceImp.setTransaction(currentTansaction);
        return new ResponseEntity<List<Transaction>>(operationServiceImp.showHistory(currentAccount), HttpStatus.OK);

    }



}
