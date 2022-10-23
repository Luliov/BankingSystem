package com.example.BankingSystem.entities.accounts.repository;

import com.example.BankingSystem.entities.accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

}
