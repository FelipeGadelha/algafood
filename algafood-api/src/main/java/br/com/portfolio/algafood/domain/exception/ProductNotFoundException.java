package br.com.portfolio.algafood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {


    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long id) {
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
                id, restaurantId));
    }
}
