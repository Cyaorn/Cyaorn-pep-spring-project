package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
@RequestMapping
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService as, MessageService ms) {
        this.accountService = as;
        this.messageService = ms;
    }

    @PostMapping("/register")
    public @ResponseBody Account registerUser() {
        return null;
    }

    @PostMapping("/login")
    public @ResponseBody Account loginUser() {
        return null;
    }

    @PostMapping("/messages")
    public @ResponseBody Message createMessage() {
        return null;
    }

    @GetMapping("/messages")
    public @ResponseBody List<Message> getAllMessages() {
        return null;
    }

    @GetMapping("/messages/{message_id}")
    public @ResponseBody Message getMessageById(@PathVariable int message_id) {
        return null;
    }

    @DeleteMapping("/messages/{message_id}")
    public @ResponseBody Message deleteMessage(@PathVariable int message_id) {
        return null;
    }

    @PatchMapping("/messages")
    public @ResponseBody Message updateMessage(@RequestParam String message_text) {
        return null;
    }

    @GetMapping("/accounts/{account_id}/messages")
    public @ResponseBody List<Message> getMessagesFromUser(@PathVariable int account_id) {
        return null;
    }
}

