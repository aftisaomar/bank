package com.kata.bank.contorller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.model.Account;
import com.kata.bank.model.Bank;
import com.kata.bank.model.Client;
import com.kata.bank.model.Operation;
import com.kata.bank.service.Transaction;
import com.kata.bank.service.TransactionImpl;

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

    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAllAccounts(){

        return new ResponseEntity<>(this.bank.getAccountList(), HttpStatus.OK);

    }


    @GetMapping("/transaction/{id}")
    public ResponseEntity<List<Transaction>> getTRansaction(@PathVariable("id") Long id){

        Account a = bank.getAccountList().stream().filter(account -> account.getNumber() == id).findFirst().orElse(null);

        if(a != null) {

            return new ResponseEntity<>(a.getTransactions(), HttpStatus.OK);

        }else{

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        
    }

    @RequestMapping(value = "/transaction/{operation}/{amount}", method=RequestMethod.POST)
    public ResponseEntity<TransactionImpl> perfomTransaction(@RequestBody Account inputAccount, @PathVariable String operation, long amount){

        Operation op = Operation.valueOf(operation);
        Account currentAccount = bank.getAccountList().stream().filter(account -> account.getNumber() == inputAccount.getNumber()).findFirst().orElse(null);

        if(currentAccount != null){

            TransactionImpl transaction = new TransactionImpl(op, amount, new Date());

            switch (op) {
            case DEPOSIT:
                transaction.save(currentAccount);
                currentAccount.getTransactions().add(transaction);
                break;
            case WITHDRAW:
                transaction.withdraw(currentAccount);
                currentAccount.getTransactions().add(transaction);
            case CONSULT:
                 transaction.consult(currentAccount);
                 currentAccount.getTransactions().add(transaction);
                 break;
            case HISTORY:
                transaction.showHistory(currentAccount);
                currentAccount.getTransactions().add(transaction);
                break;
            default:
                break;
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);

        }
     
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);



    }











    
}
