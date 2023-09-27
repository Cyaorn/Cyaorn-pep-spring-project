package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.MessageAlreadyExistsException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    MessageRepository mr;
    // private List<Message> messageList;

    @Autowired
    public MessageService (MessageRepository mr) {
        this.mr = mr;
    }

    public Message createMessage(Message newMessage) {
        Optional<Message> search = mr.findById(newMessage.getMessage_id());
        if (search.isPresent()) {
            throw new MessageAlreadyExistsException("Message ID already exists in database");
        }
        return mr.save(newMessage);
    }

    public List<Message> getAllMessages() {
        return mr.findAll();
    }

    public Message getMessageById(int id) throws MessageNotFoundException {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            return search.get();
        }
        throw new MessageNotFoundException("Message not found in database");
    }

    public boolean deleteMessage(int id) {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            mr.delete(search.get());
            return true;
        }
        return false;
    }

    public Message updateMessage(int id, String new_text) {
        Optional<Message> search = mr.findById(id);
        if (search.isPresent()) {
            Message msg = search.get();
            msg.setMessage_text(new_text);
            mr.save(msg);
        }
        throw new MessageNotFoundException("Message not found in database");
    }

    public List<Message> getAllFromUser(int account_id) {
        return mr.findByAccountId(account_id);
    }
}
