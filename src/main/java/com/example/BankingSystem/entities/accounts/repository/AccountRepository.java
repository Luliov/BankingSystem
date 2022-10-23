package com.example.BankingSystem.entities.accounts.repository;

import com.example.BankingSystem.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(Account accountId);
}
