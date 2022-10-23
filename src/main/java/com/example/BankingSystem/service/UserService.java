package com.example.BankingSystem.service;

import com.example.BankingSystem.DTO.WireTransferDTO;
import com.example.BankingSystem.entities.accounts.Account;
import com.example.BankingSystem.entities.users.ThirdParty;
import com.example.BankingSystem.entities.accounts.repository.AccountRepository;
import com.example.BankingSystem.entities.users.repository.AccountHolderRepository;
import com.example.BankingSystem.entities.users.AccountHolder;
import com.example.BankingSystem.entities.users.repository.ThirdPartyRepository;
import com.example.BankingSystem.utilities.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public AccountHolder createAccountHolder(AccountHolder accountHolder){
        return accountHolderRepository.save(accountHolder);
    }

    public ThirdParty createThirdParty(ThirdParty thirdParty){
        //Third-party users must be added to the database by an admin.
        return thirdPartyRepository.save(thirdParty);
    }

    public Money adminSetBalance(Long accountId, Money amount) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setBalance(amount);
        account = accountRepository.save(account);
        return account.getBalance();
    }

    public void accountHolderAccessAccounts() {
// AccountHolders should be able to access their own accounts and only their accounts when
// passing the correct credentials using Basic Auth.
    }

    public Money getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return account.getBalance();
    }


    public void wireTransfer(WireTransferDTO wireTransferDTO) throws Exception {
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


    public void statusAccount(){

    }
}
/*
servcieaacount:
controller: createsaving ya se recibe ob tipo savingaccpunt
se llama al repos pasado l obj llegado o el service con un metodo createsavingaccount
que reciba el account y llame al repos y lo guarda
en el service semira si hay datos malos
transferencia acción en el service, llega cuenta origen destino, se hacen las va,lidac, saldo, bloqueada



 */

