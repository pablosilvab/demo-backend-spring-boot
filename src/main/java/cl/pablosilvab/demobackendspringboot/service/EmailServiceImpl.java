package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.to}")
    private String to;

    public void sendSimpleMessage(Message m) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMailMessage mimeMailMessage = new MimeMailMessage(mimeMessage);
        mimeMailMessage.setFrom(m.getName());
        mimeMailMessage.setTo(to);
        mimeMailMessage.setSubject(m.getSubject());
        mimeMailMessage.setText(m.getText());
        emailSender.send(mimeMailMessage.getMimeMessage());

    }
}
