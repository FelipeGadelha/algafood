package br.com.portfolio.algafood.infra.service.email;

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
//    protected MimeMessage mimeMessageHelper(MimeMessage mimeMessage, Email email) throws MessagingException {
//        var message = process(email);
//        var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
//        helper.setSubject(email.getSubject());
//        helper.setTo(email.getTo().toArray(new String[0]));
//        helper.setFrom(emailProperties.getSender());
//        helper.setText(message, true);
//        return helper.getMimeMessage();
//
//    }
//
//    protected MimeMessage mimeMessageHelper(MimeMessage mimeMessage, Email email, String from) throws MessagingException {
//        var message = process(email);
//        var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
//        helper.setSubject(email.getSubject());
//        helper.setTo(email.getTo().toArray(new String[0]));
//        helper.setFrom(from);
//        helper.setText(message, true);
//        return helper.getMimeMessage();
//
//    }
}
