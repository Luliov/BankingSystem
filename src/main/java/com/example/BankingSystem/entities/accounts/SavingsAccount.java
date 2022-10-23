package com.example.BankingSystem.entities.accounts;

import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SavingsAccount extends Account{

    private BigDecimal interestRate;
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    public SavingsAccount() {
    }

    public SavingsAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        this.interestRate = new BigDecimal(0.0025);
        this.secretKey = secretKey;
        this.minimumBalance = new Money(BigDecimal.valueOf(1000));
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws Exception {

        if(interestRate.compareTo(new BigDecimal(0.0025)) == -1 || interestRate.compareTo(new BigDecimal(0.5)) == 1){
           throw new Exception("Interest rate should be between 0.0025 and 0.5");
        } else{
            this.interestRate = interestRate;
        }
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) throws Exception {
        if (minimumBalance.getAmount().compareTo(new BigDecimal(1000)) == -1 || minimumBalance.getAmount().compareTo(new BigDecimal(100)) == 1) {
            throw new Exception("Minimum balance should be between 1000 and 100");
        } else {
            this.minimumBalance = minimumBalance;
        }
    }






}
