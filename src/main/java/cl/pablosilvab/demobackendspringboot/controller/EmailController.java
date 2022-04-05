package cl.pablosilvab.demobackendspringboot.controller;


import cl.pablosilvab.demobackendspringboot.model.Message;
import cl.pablosilvab.demobackendspringboot.model.Project;
import cl.pablosilvab.demobackendspringboot.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/")
    public ResponseEntity<Message> sendMail(@RequestBody Message message) {
        log.info("send mail {}", message.toString());
        emailService.sendSimpleMessage(message);
        return ResponseEntity.ok().build();
    }
}
