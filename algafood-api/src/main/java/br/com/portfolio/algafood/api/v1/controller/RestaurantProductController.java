package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.ProductRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductRs;
import br.com.portfolio.algafood.domain.entity.Product;
import br.com.portfolio.algafood.domain.service.RestaurantProductService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    private final RestaurantProductService restaurantProductService;

    @Autowired
    public RestaurantProductController(RestaurantProductService restaurantProductService) {
        this.restaurantProductService = restaurantProductService;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    public ResponseEntity<List<ProductRs>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantProductService.findAll(restaurantId)
                .stream()
                .map(ProductRs::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @JsonView(View.Detail.class)
    public ResponseEntity<ProductRs> findById(@PathVariable Long restaurantId, @PathVariable Long id) {
        final var product = restaurantProductService.findById(restaurantId, id);
        return ResponseEntity.ok(new ProductRs(product));
    }

    @PostMapping
    @JsonView(View.Detail.class)
    public ResponseEntity<ProductRs> save(@PathVariable Long restaurantId, @RequestBody @Valid ProductRq productRq) {
        var product = restaurantProductService.save(productRq.convert(), restaurantId);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }
    @PutMapping("/{id}")
    @JsonView(View.Detail.class)
    public ResponseEntity<ProductRs> update(@PathVariable Long restaurantId,
                                            @PathVariable Long id,
                                            @RequestBody @Valid ProductRq productRq) {
        var product = restaurantProductService.update(productRq.convert(), restaurantId, id);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }



}
