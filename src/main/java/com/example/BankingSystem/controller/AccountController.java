package com.example.BankingSystem.controller;

import com.example.BankingSystem.DTO.AccountDTO;
import com.example.BankingSystem.entities.accounts.Account;
import com.example.BankingSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create-checking-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account createNewCheckingAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createCheckingAccount(accountDTO);
    }

    @PostMapping("/create-savings-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account createNewSavingsAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createSavingsAccount(accountDTO);
    }

    @PostMapping("/create-credit-card-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account createNewCreditCardAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createCreditCardAccount(accountDTO);
    }
}
