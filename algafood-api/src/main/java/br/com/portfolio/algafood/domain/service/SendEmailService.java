package br.com.portfolio.algafood.domain.service;


import br.com.portfolio.algafood.domain.entity.Message;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

public interface SendEmailService {

    void send(Message message);

}
