package br.com.portfolio.algafood.config.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
@Component
@ConfigurationProperties("algafood.mail")
public class EmailProperties {

    @NotNull private String sender;
    @NotNull private Impl impl = Impl.FAKE;
    private Sandbox sandbox = new Sandbox();

    public enum Impl { SMTP, FAKE, SANDBOX }
    public class Sandbox {
        private String to;
        public String getTo() { return to; }
        public void setTo(String to) { this.to = to; }
    }

    public String getSender() { return sender; }
    public Impl getImpl() { return impl; }
    public Sandbox getSandbox() { return sandbox; }
    public void setSender(String sender) { this.sender = sender; }

    public void setSandbox(Sandbox sandbox) { this.sandbox = sandbox; }

    public void setImpl(Impl impl) { this.impl = impl; }
}
