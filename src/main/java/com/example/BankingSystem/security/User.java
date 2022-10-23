package com.example.BankingSystem.security;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String name;

//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
//    @JsonIgnore
//    private List<Role> roles = new ArrayList<>();

    private String role;

    public User(){}

    public User(String username, String password, String name, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        //roles.add(new Role(role_name,this));
    }
}
