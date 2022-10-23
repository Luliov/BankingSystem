package com.example.BankingSystem.service;

import com.example.BankingSystem.entities.accounts.CheckingAccount;
import com.example.BankingSystem.entities.accounts.CreditCardAccount;
import com.example.BankingSystem.entities.accounts.SavingsAccount;
import com.example.BankingSystem.entities.accounts.StudentCheckingAccount;
import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceInterface {

    List<CheckingAccount> createCheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee, Money balance, Money minimumBalance, BigDecimal monthlyMaintenanceFee, String secretKey);
    List<StudentCheckingAccount> createCheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee,Money balance, String secretKey);
    List<SavingsAccount> createSavingsAccount (AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money penaltyFee, String secretKey, Money minimumBalance);
    List<CreditCardAccount> createCreditCardAccount (AccountHolder primaryOwner, AccountHolder secondaryOwner, Money penaltyFee, Money balance);
}
