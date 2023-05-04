package br.com.portfolio.algafood.infra.service.email;

import br.com.portfolio.algafood.domain.model.Email;
import br.com.portfolio.algafood.domain.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

public class FakeSendEmailService extends ManagerEmailTemplate implements SendEmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public FakeSendEmailService(FreeMarkerConfig freeMarkerConfig) {
        super(freeMarkerConfig);
    }

    @Override
    public void send(Email email) {
        var message = process(email);
        logger.info("Enviando email subject: {}", email.getSubject());
        logger.info("Enviando email to: {}", email.getTo());
        logger.info("Enviando email body: {}", message);
    }
}



