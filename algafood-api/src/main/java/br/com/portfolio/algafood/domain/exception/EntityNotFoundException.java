package br.com.portfolio.algafood.domain.exception;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String message) {
		super(message);
	}
	
}
