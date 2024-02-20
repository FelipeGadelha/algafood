package br.com.portfolio.algafood.domain.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Email {

    private final String subject; //assunto
    private final Set<String> to; //destinatário
    private final String body;
    private final Map<String, Object> properties;

    public Email(Builder builder) {
        this.subject = Objects
            .requireNonNull(builder.subject, "Email deve ter assunto");
        this.to = Objects
            .requireNonNull(builder.to, "Email deve ter destinatário");
        this.body = Objects
            .requireNonNull(builder.body, "Email deve ter corpo");
        this.properties = builder.properties;
    }
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String subject = "[No Subject]";
        private Set<String> to = new HashSet<>();
        private String body = "[No Body]";
        private Map<String, Object> properties = new HashMap<>();

        private Builder() {}

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        public Builder to(Set<String> to) {
            this.to = to;
            return this;
        }
        public Builder addTo(String to) {
            this.to.add(to);
            return this;
        }
        public Builder removeTo(String to) {
            this.to.remove(to);
            return this;
        }
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        public Builder properties(Map<String, Object> properties) {
            this.properties = properties;
            return this;
        }
        public Builder addProperty(String key, Object value) {
            this.properties.put(key, value);
            return this;
        }
        public Builder removeProperty(String key, Object value) {
            this.properties.remove(key, value);
            return this;
        }
        public Builder copy(Email email) {
            this.subject = (Objects.isNull(subject)) ? email.subject : this.subject;
            this.body = (Objects.isNull(body)) ? email.body : this.body;
            this.to = email.to;
            this.properties = email.properties;
            return this;
        }
        public Builder clone(Email email) {
            this.subject = email.subject;
            this.body = email.body;
            this.to = email.to;
            this.properties = email.properties;
            return this;
        }
        public Email build() { return new Email(this); }
    }

    public String getSubject() { return subject; }
    public Set<String> getTo() { return to; }
    public String getBody() { return body; }
    public Map<String, Object> getProperties() { return properties; }
}