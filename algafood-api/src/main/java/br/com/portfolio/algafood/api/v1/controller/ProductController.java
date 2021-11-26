package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.ProductRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductRs;
import br.com.portfolio.algafood.domain.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    public ResponseEntity<List<ProductRs>> findAll(@PathVariable Long restaurantId,
                                                   @RequestParam(required = false) boolean includeInactive) {
        return ResponseEntity.ok(productService.findAll(restaurantId, includeInactive)
                .stream()
                .map(ProductRs::new)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRs> findById(@PathVariable Long restaurantId, @PathVariable Long id) {
        final var product = productService.findById(restaurantId, id);
        return ResponseEntity.ok(new ProductRs(product));
    }

    @PostMapping
    public ResponseEntity<ProductRs> save(@PathVariable Long restaurantId, @RequestBody @Valid ProductRq productRq) {
        var product = productService.save(productRq.convert(), restaurantId);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductRs> update(@PathVariable Long restaurantId,
                                            @PathVariable Long id,
                                            @RequestBody @Valid ProductRq productRq) {
        var product = productService.update(productRq.convert(), restaurantId, id);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }
}
