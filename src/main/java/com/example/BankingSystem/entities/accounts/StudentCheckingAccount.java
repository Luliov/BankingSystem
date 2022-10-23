package com.example.BankingSystem.entities.accounts;

import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import javax.persistence.Entity;

@Entity
public class StudentCheckingAccount extends Account  {

    private String secretKey;
    public StudentCheckingAccount() {
    }

    public StudentCheckingAccount(String secretKey) {
        this.secretKey = secretKey;
    }

    public StudentCheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


}
