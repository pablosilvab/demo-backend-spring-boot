package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Message;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(Message message) throws MessagingException;
}
