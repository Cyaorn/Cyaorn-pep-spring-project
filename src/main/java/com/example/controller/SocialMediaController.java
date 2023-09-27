package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(path="/register", method=RequestMethod.POST)
    public @ResponseBody Account registerUser() {
        return null;
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public @ResponseBody Account loginUser() {
        return null;
    }

    @RequestMapping(path="/messages", method=RequestMethod.POST)
    public @ResponseBody Message createMessage() {
        return null;
    }

    @RequestMapping(path="/messages", method=RequestMethod.GET)
    public @ResponseBody List<Message> getAllMessages() {
        return null;
    }

    @RequestMapping(path="/messages/{message_id}", method=RequestMethod.GET)
    public @ResponseBody Message getMessageById() {
        return null;
    }

    @RequestMapping(path="/messages/{message_id}", method=RequestMethod.DELETE)
    public @ResponseBody Message deleteMessage() {
        return null;
    }

    @RequestMapping(path="/messages", method=RequestMethod.PATCH)
    public @ResponseBody Message updateMessage() {
        return null;
    }

    @RequestMapping(path="/accounts/{account_id}", method=RequestMethod.GET)
    public @ResponseBody List<Message> getAllMessagesByUser() {
        return null;
    }
}

