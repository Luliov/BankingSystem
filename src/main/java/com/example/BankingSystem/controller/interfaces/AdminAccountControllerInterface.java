package com.example.BankingSystem.controller.interfaces;

import com.example.BankingSystem.entities.accounts.CheckingAccount;
import com.example.BankingSystem.entities.users.AccountHolder;

import java.util.List;

public interface AdminAccountControllerInterface {
    List<CheckingAccount> createCheckingAccount(AccountHolder primaryOwner);

    interface AccountHolderControllerInterface {
    }

    interface AdminControllerInterface {

        List<CheckingAccount> createCheckingAccount(AccountHolder primaryOwner);


    }

    interface ThirdPartyUserControllerInterface {
    }
}
