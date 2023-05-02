package br.com.portfolio.algafood.infra.service;


import br.com.portfolio.algafood.config.email.EmailProperties;
import br.com.portfolio.algafood.domain.model.Email;
import br.com.portfolio.algafood.domain.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

public class SandboxSendEmailService extends SmtpSendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    @Autowired
    public SandboxSendEmailService(
        JavaMailSender mailSender,
        EmailProperties emailProperties,
        FreeMarkerConfig freeMarkerConfig
    ) {
        super(mailSender, emailProperties, freeMarkerConfig);
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
    }

    @Override
    public void send(Email email) {
        try {
            mailSender.send(mimeMessage -> {
                var message = process(email);
                var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
                helper.setSubject(email.getSubject());
                helper.setTo(emailProperties.getSandbox().getTo());
                helper.setFrom(emailProperties.getSender());
                helper.setText(message, true);
                var mime = helper.getMimeMessage();
                mailSender.send(mime);
            });
        } catch (Exception ex) {
            throw new EmailException("Não foi possível enviar o e-mail " + ex.getMessage(), ex);
        }
    }
}
