package com.example.BankingSystem.security;

import com.example.BankingSystem.entities.accounts.repository.AccountRepository;
import com.example.BankingSystem.entities.users.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("users")
    public List<User> findAll(@AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails.getUsername());
        return userRepository.findAll();
    }

}
