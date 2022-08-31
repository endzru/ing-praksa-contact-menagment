package com.example.contactmenagment.services.mail;

import com.example.contactmenagment.controllers.dto.userDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Environment environment;
    @Autowired
    SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(Email email) {
        try {
            MimeMessage mes = emailSender.createMimeMessage();
            MimeMessageHelper s = new MimeMessageHelper(mes, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(email.getProperties());
            s.setFrom(environment.getProperty("spring.mail.username"));
            s.setTo(email.getTo());
            s.setSubject(email.getSubject());
            String html = templateEngine.process(email.getTemplateName(), context);
            s.setText(html, true);
            emailSender.send(mes);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendWelcomeMail(UserRequestDTO userRequestDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", userRequestDTO.getEmail());
        map.put("password", userRequestDTO.getPassword());

        Email email = new Email();
        email.setProperties(map);
        email.setSubject("Welcome to Contact Manager");
        email.setTemplateName("email-template");
        email.setTo(userRequestDTO.getEmail());

        sendEmail(email);
    }

}
