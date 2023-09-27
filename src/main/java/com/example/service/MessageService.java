package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.MessageNotFoundException;

@Service
public class MessageService {

    private List<Message> messageList;

    public Message createMessage(Message newMessage) {
        messageList.add(newMessage);
        return newMessage;
    }

    public List<Message> getAllMessages() {
        return messageList;
    }

    public Message getMessageById(int id) throws MessageNotFoundException {
        for (Message msg : messageList) {
            if (msg.getMessage_id() == id) {
                return msg;
            }
        }
        throw new MessageNotFoundException("Message not found in database");
    }

    public boolean deleteMessage(int id) {
        Iterator<Message> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getMessage_id() == id) {
                iterator.remove(); // Iterator.remove() instead of List.remove()
                return true;
            }
        }
        return false;
    }

    public Message updateMessage(int id, String new_text) {
        for (Message msg : messageList) {
            if (msg.getMessage_id() == id) {
                msg.setMessage_text(new_text);
                return msg;
            }
        }
        throw new MessageNotFoundException("Message not found in database");
    }

    public List<Message> getMessagesFromUser(int account_id) {
        List<Message> out = new ArrayList<>();
        for (Message msg : messageList) {
            if (msg.getPosted_by() == account_id) {
                out.add(msg);
            }
        }
        return out;
    }
}
