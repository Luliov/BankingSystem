package com.example.BankingSystem.service;

import com.example.BankingSystem.DTO.AccountDTO;
import com.example.BankingSystem.DTO.WireTransferDTO;
import com.example.BankingSystem.entities.accounts.Account;
import com.example.BankingSystem.entities.accounts.CheckingAccount;
import com.example.BankingSystem.entities.accounts.CreditCardAccount;
import com.example.BankingSystem.entities.accounts.SavingsAccount;
import com.example.BankingSystem.entities.accounts.repository.CheckingAccountRepository;
import com.example.BankingSystem.entities.accounts.repository.SavingsAccountRepository;
import com.example.BankingSystem.entities.users.ThirdParty;
import com.example.BankingSystem.entities.accounts.repository.AccountRepository;
import com.example.BankingSystem.entities.users.repository.AccountHolderRepository;
import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.entities.users.repository.ThirdPartyRepository;
import com.example.BankingSystem.security.User;
import com.example.BankingSystem.security.UserRepository;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class UserService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    public AccountHolder createAccountHolder(AccountHolder accountHolder){


        accountHolder.setRole("ACCOUNTHOLDER");
        accountHolder.setPassword(passwordEncoder.encode(accountHolder.getPassword()));
        return accountHolderRepository.save(accountHolder);
    }

    public ThirdParty createThirdParty(ThirdParty thirdParty){
        //Third-party users must be added to the database by an admin.
        thirdParty.setRole("THIRDPARTY");
        thirdParty.setPassword(passwordEncoder.encode(thirdParty.getPassword()));
        return thirdPartyRepository.save(thirdParty);
    }

    public Money adminSetBalance(Long accountId, Money amount) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setBalance(amount);
        account = accountRepository.save(account);
        return account.getBalance();
    }

    public Money showBalance(Long accountId, UserDetails userDetails) throws Exception {
        Account account = accountRepository.findById(accountId).orElseThrow();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        if(user.getId() != account.getPrimaryOwner().getId() &&
           user.getId() != account.getSecondaryOwner().getId()){
            throw new Exception("wrong user data");
        }
        return account.getBalance();
    }

    public void wireTransfer(WireTransferDTO wireTransferDTO, UserDetails userDetails) throws Exception {
        //User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        AccountHolder accountHolder = accountHolderRepository.findById(wireTransferDTO.getAccountHolderId()).orElseThrow();
        Account accountOrigin = accountRepository.findById(wireTransferDTO.getOriginAccountId()).orElseThrow();

        if(accountOrigin.getPrimaryOwner().getId() != accountHolder.getId()){
            throw new Exception("You must be the account's owner");
        }else if( accountOrigin.getBalance().getAmount().subtract(accountOrigin.getPenaltyFee().getAmount()).compareTo(new BigDecimal(wireTransferDTO.getAmount())) == -1){
            throw new Exception("Insufficient funds");
        }

        Account accountDestination = accountRepository.findById(wireTransferDTO.getDestinationAccountId()).orElseThrow();
        AccountHolder accountHolderDestination = accountHolderRepository.findByName(wireTransferDTO.getOwnerName()).orElseThrow();

        if(accountDestination.getPrimaryOwner().getId() != accountHolderDestination.getId() &&
        (accountDestination.getSecondaryOwner() == null || accountDestination.getSecondaryOwner().getId() != accountHolderDestination.getId())){
            throw new Exception("The name of the recipient does not match the owner of the account");
        }

        Money money = accountOrigin.getBalance();
        money.decreaseAmount(new Money(new BigDecimal(wireTransferDTO.getAmount())));
        accountOrigin.setBalance(money);
        accountRepository.save(accountOrigin);

        money = accountDestination.getBalance();
        money.increaseAmount(new Money(new BigDecimal(wireTransferDTO.getAmount())));
        accountDestination.setBalance(money);
        accountRepository.save(accountDestination);
    }

    public void wireTransferThirdParty(WireTransferDTO wireTransferDTO, String hashedKey) throws Exception {
        ThirdParty thirdPartyHashedKey = thirdPartyRepository.findByHashedKey(hashedKey).orElseThrow();
        Account accountDestination = accountRepository.findById(wireTransferDTO.getDestinationAccountId()).orElseThrow();
        Money money = accountDestination.getBalance();
        money.increaseAmount(new Money(new BigDecimal(wireTransferDTO.getAmount())));
        accountDestination.setBalance(money);
        thirdPartyRepository.save(thirdPartyHashedKey);
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }

}

//    public Money belowMinimumBalance(Money minimumBalance, BigDecimal penaltyFee){
//        If any account drops below minimumBalance, penaltyFee should be deducted from the balance automatically
//        return null;}

//    public BigDecimal interestRateCreditCard(CreditCardAccount creditCardAccount){//
//    Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12)
//    then 1% interest will be added to the account monthly.
//    When the balance of a credit card is accessed, check to determine if it has been 1 month or more
//    since the account was created or since interested was added, and if so, add the appropriate interest to the balance.
//    }

//     public void interestSavingsAccount(AccountDTO accountDTO) {
//     BigDecimal interestRate = new BigDecimal(accountDTO.getInterestRate());
//     Money amount = new Money(new BigDecimal(accountDTO.getBalance()));
//     Money totalAmount = amount;
//     int time = SavingsAccount.getCreationDate() - (LocalDate.now());
//     int years = savingsAccount.getCreationDate().getYear() - (LocalDate.now().getYear());
//     BigDecimal yearlyInterest;
//     for (int i = 0; i == 12; i++ ){
//        yearlyInterest = totalAmount * interestRate;
//        totalAmount += yearlyInterest;
//      }
//   Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. That means
//   that if I have 1000000 in a savings account with a 0.01 //   interest rate, 1% of 1 Million is added to my account after 1 year.
//   When a savings account balance is accessed, you must determine if it has been 1 year or more since either the
//   account was created or since interest was added to the account, and add the appropriate interest to the balance if
//   necessary.}





