package com.arnus.merceariaarnus.service;

import com.arnus.merceariaarnus.model.EmailModel;
import com.arnus.merceariaarnus.model.StatusEmailEnum;
import com.arnus.merceariaarnus.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender sender;

    public void sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            sender.send(message);

            emailModel.setStatusEmailEnum(StatusEmailEnum.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmailEnum(StatusEmailEnum.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }
}