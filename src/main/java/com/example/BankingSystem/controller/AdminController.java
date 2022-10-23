package com.example.BankingSystem.controller;

import com.example.BankingSystem.controller.interfaces.UserControllerInterface;
import com.example.BankingSystem.entities.users.ThirdParty;
import com.example.BankingSystem.service.UserService;
import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController implements UserControllerInterface {

    @Autowired
    UserService userService;

    @PostMapping("/create-account-holder")
    public AccountHolder createAccountHolder(@RequestBody AccountHolder accountHolder){
        return userService.createAccountHolder(accountHolder);
    }

    @PostMapping("/create-third-party")
    public ThirdParty createThirdParty(@RequestBody ThirdParty thirdParty){
        return userService.createThirdParty(thirdParty);
    }

    @PatchMapping("/set-balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money adminSetBalance(@RequestParam Long accountId, @RequestBody Money amount) {
        return userService.adminSetBalance(accountId, amount);
    }




}
