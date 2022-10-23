package com.example.BankingSystem.entities.accounts.repository;

import com.example.BankingSystem.entities.accounts.CreditCardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardAccountRepository extends JpaRepository <CreditCardAccount, Long>{
}
