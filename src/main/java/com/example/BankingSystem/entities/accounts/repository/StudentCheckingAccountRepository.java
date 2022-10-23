package com.example.BankingSystem.entities.accounts.repository;

import com.example.BankingSystem.entities.accounts.StudentCheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingAccountRepository extends JpaRepository<StudentCheckingAccount, Long> {

    //List<CheckingAccount> createCheckingAccount(CheckingAccount checkingAccount);

}
