package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
// import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;
import com.example.exception.AccountAlreadyExistsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository ar;
    // private List<Account> accountList;

    @Autowired
    public AccountService (AccountRepository ar) {
        this.ar = ar;
    }

    public Account registerUser(Account newUser) throws AccountAlreadyExistsException {
        Optional<Account> search = ar.findById(newUser.getAccount_id());
        if (search.isPresent()) {
            throw new AccountAlreadyExistsException("Account ID already exists in database");
        }
        return ar.save(newUser);
    }

    public Account userExists(int account_id) throws AccountNotFoundException {
        Optional<Account> search = ar.findById(account_id);
        if (search.isPresent()) {
            return search.get();
        }
        throw new AccountNotFoundException("Account not found in list");
    }

}
