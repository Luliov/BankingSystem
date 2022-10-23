package com.example.BankingSystem.entities.users;

import com.example.BankingSystem.entities.accounts.Account;
import com.example.BankingSystem.security.User;
import com.example.BankingSystem.utilities.Address;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User {

    @Embedded
    private Address primaryAddress;
    private LocalDate dateOfBirth;
    private String mailingAddress;
    public String password;
    @OneToMany(mappedBy = "primaryOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> primaryOwnerList;
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryOwnerList;

    public AccountHolder() {
    }

    public AccountHolder(String username, String password, String name, Address primaryAddress, LocalDate dateOfBirth, String mailingAddress, List<Account> primaryOwnerList, List<Account> secondaryOwnerList) {
        super(username, password, name, "ACCOUNTHOLDER");
        this.primaryAddress = primaryAddress;
        this.dateOfBirth = dateOfBirth;
        this.mailingAddress = mailingAddress;
        this.password = password;
        this.primaryOwnerList = primaryOwnerList;
        this.secondaryOwnerList = secondaryOwnerList;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getPrimaryOwnerList() {
        return primaryOwnerList;
    }

    public void setPrimaryOwnerList(List<Account> primaryOwnerList) {
        this.primaryOwnerList = primaryOwnerList;
    }

    public List<Account> getSecondaryOwnerList() {
        return secondaryOwnerList;
    }

    public void setSecondaryOwnerList(List<Account> secondaryOwnerList) {
        this.secondaryOwnerList = secondaryOwnerList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
