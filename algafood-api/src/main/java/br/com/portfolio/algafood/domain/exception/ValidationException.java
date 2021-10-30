package br.com.portfolio.algafood.domain.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
