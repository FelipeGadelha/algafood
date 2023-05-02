package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.Product;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantService restaurantService;
    private static final String MSG_RESTAURANT_PRODUCT_NOT_FOUND = "Não existe um cadastro de produto " +
            "com código %d para o restaurante de código %d";

    @Autowired
    public ProductService(RestaurantService restaurantService, ProductRepository productRepository) {
        this.restaurantService = restaurantService;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> findAll(Long restaurantId, boolean includeInactive) {
        return (includeInactive) ? productRepository.findByRestaurantId(restaurantId)
                : productRepository.findByActiveRestaurants(restaurantId);
    }

    @Transactional
    public Product findById(Long restaurantId, Long id) {
        return productRepository.findById(restaurantId, id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format(MSG_RESTAURANT_PRODUCT_NOT_FOUND, id, restaurantId)));
    }

    @Transactional
    public Product save(Product product, Long restaurantId) {
        var restaurant = restaurantService.findById(restaurantId);
        product = Product.builder()
                .clone(product)
                .restaurant(restaurant)
                .build();
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product updated, Long restaurantId, Long id) {
        var restaurant = restaurantService.findById(restaurantId);
        var product = restaurant.getProducts()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format(MSG_RESTAURANT_PRODUCT_NOT_FOUND, id, restaurantId)));
        var result = Product.builder()
                .clone(product)
                .copy(updated)
                .build();
        return productRepository.save(result);
    }
}
