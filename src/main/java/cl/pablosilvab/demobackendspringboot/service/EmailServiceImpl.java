package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.to}")
    private String to;

    public void sendSimpleMessage(Message m) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(m.getSubject());
        message.setText(m.getText());
        emailSender.send(message);

    }
}
