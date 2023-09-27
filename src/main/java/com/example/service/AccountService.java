package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
// import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;

@Service
public class AccountService {

    private List<Account> accountList;

    public Account registerUser(Account newUser) {
        accountList.add(newUser);
        return newUser;
    }

    public Account userExists(int account_id) throws AccountNotFoundException {
        for (Account user : accountList) {
            if (user.getAccount_id() == account_id) {
                return user;
            }
        }
        throw new AccountNotFoundException("Account not found in list");
    }

}
