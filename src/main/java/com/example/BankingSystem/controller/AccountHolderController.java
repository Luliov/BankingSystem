package com.example.BankingSystem.controller;

import com.example.BankingSystem.DTO.WireTransferDTO;
import com.example.BankingSystem.service.UserService;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountHolderController {


    @Autowired
    UserService userService;

    @PostMapping("/wire-transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void wireTransfer(@AuthenticationPrincipal UserDetails userDetails, @RequestBody WireTransferDTO wireTransferDTO) throws Exception {
        userService.wireTransfer(wireTransferDTO, userDetails);
    }

    @GetMapping("/show-balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money showBalance(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long accountId) throws Exception {
        return userService.showBalance(accountId, userDetails);
    }



}



