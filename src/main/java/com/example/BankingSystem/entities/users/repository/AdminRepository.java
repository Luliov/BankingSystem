package com.example.BankingSystem.entities.users.repository;

import com.example.BankingSystem.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long> {
}
