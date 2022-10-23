package com.example.BankingSystem.entities.accounts.repository;

import com.example.BankingSystem.entities.accounts.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {


}
