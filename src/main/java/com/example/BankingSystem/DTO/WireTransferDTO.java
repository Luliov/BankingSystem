package com.example.BankingSystem.DTO;

public class WireTransferDTO {

    private Long originAccountId;
    private Long destinationAccountId;
    private String amount;
    private Long accountHolderId;
    private String ownerName;
    private String hashedKey;


    public WireTransferDTO(Long originAccountId, Long destinationAccountId, String amount, Long accountHolderId, String ownerName) {
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.accountHolderId = accountHolderId;
        this.ownerName = ownerName;
    }

    public WireTransferDTO() {
    }

    public WireTransferDTO(Long destinationAccountId, String amount, String ownerName, String hashedKey) {
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.ownerName = ownerName;
        this.hashedKey = hashedKey;
    }


    public Long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(Long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(Long accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
