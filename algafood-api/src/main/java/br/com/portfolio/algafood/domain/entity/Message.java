package br.com.portfolio.algafood.domain.entity;

import br.com.portfolio.algafood.domain.exception.EmailException;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;

public class Message {
    private String subject = "[No Subject]";
    private Set<String> from = new HashSet<>();
    private Set<String> to = new HashSet<>(); // destinatario
    private String body = "";

//    private Set<String> bcc = new HashSet<>(); // Blind carbom Copy - Cópia oculta
//    private Set<String> cc = new HashSet<>(); // carbom Copy - com copia
//    private Multimap<String, String> headers = ArrayListMultimap.create();
//    private List<String> replyTo = new ArrayList<>();
//    private List<EmailAttachment> attachments = new ArrayList<>(); // anexos

    private Message(Builder builder) {
        this.subject = builder.subject;
        this.from = builder.from;
        this.to = builder.to;
        this.body = builder.body;
    }
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String subject = "[No Subject]";
        private Set<String> from = new HashSet<>();
        private Set<String> to = new HashSet<>(); // destinatario
        private String body;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        public Builder from(Set<String> from) {
            this.from = from;
            return this;
        }
        public Builder addFrom(String from) {
            this.from.add(from);
            return this;
        }
        public Builder removeFrom(String from) {
            this.from.remove(from);
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
    }

    public String getSubject() { return subject; }
    public Set<String> getFrom() { return from; }
    public Set<String> getTo() { return to; }
    public String getBody() { return body; }
}