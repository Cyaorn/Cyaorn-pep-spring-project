package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.AccountNotFoundException;
import com.example.exception.MessageAlreadyExistsException;
import com.example.exception.MessageNotFoundException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService as, MessageService ms) {
        this.accountService = as;
        this.messageService = ms;
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> registerUser(@RequestBody Account newUser) {  
        String username = newUser.getUsername();
        String password = newUser.getPassword();                                              
        if (username.length() > 0 && password.length() >= 4) {
            try {
                return ResponseEntity
                    .status(200)
                    .body(accountService.registerUser(username, password));
            } catch (AccountAlreadyExistsException e) {
                return ResponseEntity
                    .status(409)
                    .body(null);
            }
        }
        return ResponseEntity
            .status(400)
            .body(null);
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> loginUser(@RequestBody Account login) {
        String username = login.getUsername();
        String password = login.getPassword();
        try {
            return ResponseEntity.status(200).body(accountService.loginUser(username, password));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> createMessage(@RequestBody Message newMessage) {
        int posted_by = newMessage.getPosted_by();
        String message_text = newMessage.getMessage_text();
        long time_posted_epoch = newMessage.getTime_posted_epoch();
        try {
            if (message_text.length() > 0 && message_text.length() < 255) {
                return ResponseEntity
                    .status(200)
                    .body(messageService.createMessage(posted_by, message_text, time_posted_epoch));
            }
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Message> getMessageById(@PathVariable int message_id) {
        return ResponseEntity.status(200).body(messageService.getMessageById(message_id));
    }

    @DeleteMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> deleteMessage(@PathVariable int message_id) {
        return ResponseEntity.status(200).body(messageService.deleteMessage(message_id));
    }

    @PatchMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> updateMessage(@RequestBody Message newMessage,
                                                               @PathVariable int message_id) {
        String message_text = newMessage.getMessage_text();
        try {
            if (message_text.length() > 0 && message_text.length() < 255) {
                return ResponseEntity
                    .status(200)
                    .body(messageService.updateMessage(message_id, message_text));
            }
        } catch (MessageNotFoundException e) {

        }
        return ResponseEntity.status(400).body(0);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllFromUser(@PathVariable int account_id) {
        return ResponseEntity.status(200).body(messageService.getAllFromUser(account_id));
    }
}

