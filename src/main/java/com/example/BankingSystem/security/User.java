package com.example.BankingSystem.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        roles.add(new Role("ADMIN", this));
    }

    //se crea un rol cuandos e crea el usuario
}
