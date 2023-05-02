package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.Email;

public interface SendEmailService {
    void send(Email email);
}
