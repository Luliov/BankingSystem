package com.example.BankingSystem.entities.users;

import com.example.BankingSystem.security.User;
import javax.persistence.*;

@Entity
public class Admin extends User {

    private String password;

    public Admin(String password) {
        this.password = password;
    }

    public Admin(String username, String password, String name) {
        super(username, password, name, "ADMIN");
        this.password = password;
    }

    public Admin() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}






