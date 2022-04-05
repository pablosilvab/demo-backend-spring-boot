package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Message;

public interface EmailService {
    void sendSimpleMessage(Message message);
}
