package br.com.portfolio.algafood.config.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull private String from;

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
}
