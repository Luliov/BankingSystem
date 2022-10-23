package com.example.BankingSystem.entities.users;

import com.example.BankingSystem.entities.accounts.Account;
import com.example.BankingSystem.utilities.Address;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Address primaryAddress;
    private LocalDate dateOfBirth;
    private String mailingAddress;
    @OneToMany(mappedBy = "primaryOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> primaryOwnerList;
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryOwnerList;

    public AccountHolder() {
    }

    public AccountHolder(String name, Address primaryAddress, LocalDate dateOfBirth, String mailingAddress) {
        this.name = name;
        this.primaryAddress = primaryAddress;
        this.dateOfBirth = dateOfBirth;
        this.mailingAddress = mailingAddress;
    }

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
