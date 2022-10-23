package com.example.BankingSystem.entities.accounts;

import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.utilities.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CreditCardAccount extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;
    private BigDecimal interestRate;

    public CreditCardAccount(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance) {
        super(primaryOwner, secondaryOwner, balance);
        this.creditLimit = new Money(BigDecimal.valueOf(100));
        this.interestRate = new BigDecimal(0.2);
    }

    public CreditCardAccount() {

    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) throws Exception {

        if(creditLimit.getAmount().compareTo(BigDecimal.valueOf(100)) == 1 || creditLimit.getAmount().compareTo(BigDecimal.valueOf(10000)) == -1){
            throw new Exception("Interest rate should be between 0.0025 and 0.5");
        } else{
            this.creditLimit = creditLimit;
        }
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) throws Exception {
        if(interestRate.compareTo(new BigDecimal(0.2)) == -1 || interestRate.compareTo(new BigDecimal(0.1)) == 1){
            throw new Exception("Interest rate should be between 0.1 and 0.2");
        } else{
            this.interestRate = interestRate;
        }
    }

}
