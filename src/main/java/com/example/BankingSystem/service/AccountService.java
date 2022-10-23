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

    public void deleteAccount(){

    }

    public BigDecimal interestRateCreditCard(CreditCardAccount creditCardAccount){

        // Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12)
        // then 1% interest will be added to the account monthly.
        return null;
    }

    public BigDecimal interestRateSavingsAccount(){
        //  Interest on savings accounts is added to the account annually at the rate of specified
        // interestRate per year. That means that if I have 1000000 in a savings account with a 0.01
        // interest rate, 1% of 1 Million is added to my account after 1 year.
        return null;
    }


    public void accessSavingsAccount(SavingsAccount savingsAccount) {
        int years = savingsAccount.getCreationDate().getYear() - (LocalDate.now().getYear());
        if(savingsAccount.getInterestRate()){

        //if(years > 1 ) || savingsAccount.getInterestRate() ) {

        }
//  When a savings account balance is accessed, you must determine if it has been 1 year or more since either the
//  account was created or since interest was added to the account, and add the appropriate interest to the balance if
//        necessary.
    }

    public void balanceCreditCardAccount() {
//  When the balance of a credit card is accessed, check to determine if it has been 1 month or more
//  since the account was created or since interested was added, and if so, add the appropriate interest to the balance.
    }

    public Money belowMinimumBalance(Account account){
        account = checkingAccountRepository.getReferenceById().getMinimumBalance();

        if (account.getBalance() < 1) {
            // If any account drops below minimumBalance, penaltyFee should be deducted from the balance automatically
            return null;
        }
    }





}
