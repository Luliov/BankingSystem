package com.example.BankingSystem.controller;

import com.example.BankingSystem.DTO.WireTransferDTO;
import com.example.BankingSystem.service.UserService;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountHolderController {


    @Autowired
    UserService userService;

    @PostMapping("/wire-transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void wireTransfer(@RequestBody WireTransferDTO wireTransferDTO) throws Exception {
        userService.wireTransfer(wireTransferDTO);
    }

    @GetMapping("/get-balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalance(@RequestParam Long accountId) {
        return userService.getBalance(accountId);
    }



}



