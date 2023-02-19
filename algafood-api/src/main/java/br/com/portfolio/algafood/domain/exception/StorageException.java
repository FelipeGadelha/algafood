package br.com.portfolio.algafood.domain.exception;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StorageException(String mensagem) {
        super(mensagem);
    }

    public StorageException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}