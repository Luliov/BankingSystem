package com.example.BankingSystem.controller;

import com.example.BankingSystem.DTO.WireTransferDTO;
import com.example.BankingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController {

    @Autowired
    UserService userService;

    @PostMapping("/wire-transfer-third-party")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void wireTransferThirdParty(@RequestHeader(HttpHeaders.AUTHORIZATION) String hashedKey, @RequestBody WireTransferDTO wireTransferDTO) throws Exception {
        userService.wireTransferThirdParty(wireTransferDTO, hashedKey);
    }


}
