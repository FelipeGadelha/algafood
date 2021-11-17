package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Product;
import br.com.portfolio.algafood.domain.exception.ProductNotFoundException;
import br.com.portfolio.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantProductService {

    private final RestaurantService restaurantService;
    private final ProductRepository productRepository;

    @Autowired
    public RestaurantProductService(RestaurantService restaurantService, ProductRepository productRepository) {
        this.restaurantService = restaurantService;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> findAll(Long restaurantId) { return restaurantService.findById(restaurantId).getProducts(); }

    @Transactional
    public Product findById(Long restaurantId, Long id) {
        return productRepository.findById(restaurantId, id)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, id));
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
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, id));
        var result = Product.builder()
                .clone(product)
                .copy(updated)
                .build();
        return productRepository.save(result);
    }
}
