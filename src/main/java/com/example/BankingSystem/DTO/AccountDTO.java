package com.example.BankingSystem.DTO;

public class AccountDTO {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String balance;
    private String secretKey;
    private Double creditLimit;
    private Double interestRate;

    private Double minimumBalance;

    public AccountDTO(Long primaryOwnerId, Long secondaryOwnerId, String balance, String secretKey) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
         this.secretKey = secretKey;
    }

    public AccountDTO(){}

    public String getBalance() {
        return balance;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

}
