package com.example.BankingSystem;

import com.example.BankingSystem.entities.accounts.CheckingAccount;
import com.example.BankingSystem.entities.accounts.repository.CheckingAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingSystemApplicationTests {

	CheckingAccount checkingAccount;

	@Autowired
	CheckingAccountRepository checkingAccountRepository;
//
//	@BeforeEach
//	void setUp(){
//		checkingAccount = checkingAccountRepository.save(new CheckingAccount());
//	}
//
//	@AfterEach
//	void tearDown(){}

	@Test
	void contextLoads(){}
}
