package com.example.BankingSystem.entities.accounts;

import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CheckingAccount extends Account {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private String secretKey;

    public CheckingAccount(){
    }

    public CheckingAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance);
        this.minimumBalance = new Money(BigDecimal.valueOf(250));
        this.monthlyMaintenanceFee = new BigDecimal(12);
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
