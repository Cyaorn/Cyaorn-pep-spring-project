package com.example.service;

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

    // @Autowired
    private AccountRepository ar;
    // private List<Account> accountList;

    @Autowired
    public AccountService (AccountRepository ar) {
        this.ar = ar;
    }

    public Account registerUser(String username, String password) throws AccountAlreadyExistsException {
        Account search = ar.findByUsername(username);
        if (search != null) {
            throw new AccountAlreadyExistsException("Account ID already exists in database");
        }
        return ar.save(new Account(username, password));
    }

    public Account loginUser(String username, String password) throws AccountNotFoundException {
        Account search = ar.findByLogin(username, password);
        if (search != null) {
            return search;
        }
        throw new AccountNotFoundException("Login information doesn't exist in database");
    }

    public Account userExists(int account_id) throws AccountNotFoundException {
        Optional<Account> search = ar.findById(account_id);
        if (search.isPresent()) {
            return search.get();
        }
        throw new AccountNotFoundException("Account not found in list");
    }

}
