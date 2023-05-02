package br.com.portfolio.algafood.config.email;

import br.com.portfolio.algafood.domain.service.SendEmailService;
import br.com.portfolio.algafood.infra.service.FakeSendEmailService;
import br.com.portfolio.algafood.infra.service.SandboxSendEmailService;
import br.com.portfolio.algafood.infra.service.SmtpSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Configuration
public class EmailConfig {

    private final EmailProperties emailProperties;
    private JavaMailSender mailSender;
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    public EmailConfig(
        EmailProperties emailProperties,
        JavaMailSender mailSender,
        FreeMarkerConfig freeMarkerConfig
    ) {
        this.emailProperties = emailProperties;
        this.mailSender = mailSender;
        this.freeMarkerConfig = freeMarkerConfig;
    }

    @Bean
    public SendEmailService sendEmailService() {
        return switch (emailProperties.getImpl()) {
            case FAKE -> new FakeSendEmailService(freeMarkerConfig);
            case SMTP -> new SmtpSendEmailService(mailSender, emailProperties, freeMarkerConfig);
            case SANDBOX
                -> new SandboxSendEmailService(mailSender, emailProperties, freeMarkerConfig);
        };
    }
}
