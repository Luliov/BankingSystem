package com.example.BankingSystem.service;

import com.example.BankingSystem.DTO.AccountDTO;
import com.example.BankingSystem.entities.accounts.*;
import com.example.BankingSystem.entities.accounts.repository.CheckingAccountRepository;
import com.example.BankingSystem.entities.accounts.repository.CreditCardAccountRepository;
import com.example.BankingSystem.entities.accounts.repository.SavingsAccountRepository;
import com.example.BankingSystem.entities.accounts.repository.StudentCheckingAccountRepository;
import com.example.BankingSystem.entities.users.repository.AccountHolderRepository;
import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    CreditCardAccountRepository creditCardAccountRepository;
    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    public Account createCheckingAccount(AccountDTO accountDTO) {
        Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow();
        AccountHolder secondaryOwner = null;
        if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
        }
        String secretKey = new String(accountDTO.getSecretKey());

        if(Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() > 24){
            return checkingAccountRepository.save(new CheckingAccount(primaryOwner, secondaryOwner, balance, secretKey));
        }
        return studentCheckingAccountRepository.save(new StudentCheckingAccount(primaryOwner, secondaryOwner, balance, secretKey));
    }

    public SavingsAccount createSavingsAccount(AccountDTO accountDTO) {
        Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow();
        AccountHolder secondaryOwner = null;
        if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
        }
         String secretKey = new String(accountDTO.getSecretKey());
        return savingsAccountRepository.save(new SavingsAccount(primaryOwner, secondaryOwner, balance, secretKey));
    }

    public CreditCardAccount createCreditCardAccount(AccountDTO accountDTO) {
        Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow();
        AccountHolder secondaryOwner = null;
        if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
        }
        return creditCardAccountRepository.save(new CreditCardAccount(primaryOwner, secondaryOwner, balance));
    }
}
