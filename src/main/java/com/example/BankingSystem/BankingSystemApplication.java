package com.example.BankingSystem;

import com.example.BankingSystem.entities.users.Admin;
import com.example.BankingSystem.entities.users.repository.AdminRepository;
import com.example.BankingSystem.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) { SpringApplication.run(BankingSystemApplication.class, args);}
	@Override
	public void run(String... args) throws Exception {

		adminRepository.save(new Admin("lili", passwordEncoder.encode("9874"),"Lili"));
		//User user = userRepository.save(new User("lili", passwordEncoder.encode("9874"), "ADMIN"));
		//roleRepository.save(new Role("ADMIN", user));

		//AccountHolder accountHolder = new AccountHolder("lili2", passwordEncoder.encode("9874"), )


		//User user = userRepository.save(new User("lili", passwordEncoder.encode("9874")));
		//roleRepository.save(new Role("USER",user));
		//adminRepository.save(new Admin("lili", "123456", "Lili"));
	}


}
