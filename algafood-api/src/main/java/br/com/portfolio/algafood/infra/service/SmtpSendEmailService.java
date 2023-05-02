package br.com.portfolio.algafood.infra.service;

import br.com.portfolio.algafood.config.email.EmailProperties;
import br.com.portfolio.algafood.domain.entity.Message;
import br.com.portfolio.algafood.domain.exception.EmailException;
import br.com.portfolio.algafood.domain.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SmtpSendEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties properties;

    @Autowired
    public SmtpSendEmailService(JavaMailSender mailSender, EmailProperties properties) {
        this.mailSender = mailSender;
        this.properties = properties;
    }

    @Override
    public void send(Message message) {
        var mimeMessage = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(message.getSubject());
            helper.setText(message.getBody(), true);
            helper.setTo(message.getTo().toArray(new String[0]));
            helper.setFrom(properties.getFrom());
        } catch (MessagingException e) {
            throw new EmailException("Não foi possível enviar sua mensagem", e);
        }


        mailSender.send(mimeMessage);
    }
}
