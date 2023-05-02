package br.com.portfolio.algafood.infra.service;

import br.com.portfolio.algafood.domain.exception.EmailException;
import br.com.portfolio.algafood.domain.model.Email;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

public abstract class ManagerEmailTemplate {

    private final FreeMarkerConfig freeMarkerConfig;

    protected ManagerEmailTemplate(FreeMarkerConfig freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }

    public String process(Email email) {
        try {
            var template = freeMarkerConfig.getConfiguration()
                    .getTemplate(email.getBody());
            return FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, email.getProperties());
        }catch (Exception ex) {
            throw new EmailException("Não foi possível montar o template do e-mail", ex);
        }
    }
}
