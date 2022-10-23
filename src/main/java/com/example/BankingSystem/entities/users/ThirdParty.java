package com.example.BankingSystem.entities.users;

import com.example.BankingSystem.security.User;

import javax.persistence.*;

@Entity
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(String username, String password, String name, String hashedKey) {
        super(username, password, name, "THIRDPARTY");
        this.hashedKey = hashedKey;
    }

    public ThirdParty() {
    }


    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

}
