package cl.pablosilvab.demobackendspringboot.service;

import cl.pablosilvab.demobackendspringboot.model.Message;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.to}")
    private String to;

    @SneakyThrows
    public void sendSimpleMessage(Message m)  {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setFrom("Contact - <info@pablosilvab.github.io/>");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(m.getSubject());
        mimeMessageHelper.setText(m.getText());
        emailSender.send(mimeMessageHelper.getMimeMessage());

    }
}
