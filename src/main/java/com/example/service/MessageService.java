package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private AccountRepository ar;
    private MessageRepository mr;

    @Autowired
    public MessageService (AccountRepository ar, MessageRepository mr) {
        this.ar = ar;
        this.mr = mr;
    }

    public Message createMessage(int posted_by, String message_text, long time_posted_epoch) {
        Optional<Account> search = ar.findById(posted_by);
        if (search.isPresent()) {
            return mr.save(new Message(posted_by, message_text, time_posted_epoch));
        }
        throw new AccountNotFoundException("Poster ID not found in database");
        
    }

    public List<Message> getAllMessages() {
        return mr.findAll();
    }

    public Message getMessageById(int id) {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            return search.get();
        }
        return null;
    }

    public int deleteMessage(int id) {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            mr.delete(search.get());
            return 1;
        }
        return 0;
    }

    public int updateMessage(int id, String new_text) {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            Message msg = search.get();
            msg.setMessage_text(new_text);
            mr.save(msg);
            return 1;
        }
        throw new MessageNotFoundException("Message not found in database");
    }

    public List<Message> getAllFromUser(int account_id) {
        return mr.findByAccountId(account_id);
    }
}
