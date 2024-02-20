package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.ProductControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.ProductRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductRs;
import br.com.portfolio.algafood.domain.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products")
public class ProductController implements ProductControllerOpenApi {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    @JsonView(View.Basic.class)
    @Override public ResponseEntity<List<ProductRs>> findAll(@PathVariable Long restaurantId,
                                                   @RequestParam(required = false) boolean includeInactive) {
        return ResponseEntity.ok(productService.findAll(restaurantId, includeInactive)
                .stream()
                .map(ProductRs::new)
                .toList());
    }

    @GetMapping("/{id}")
    @Override public ResponseEntity<ProductRs> findById(@PathVariable Long restaurantId, @PathVariable Long id) {
        final var product = productService.findById(restaurantId, id);
        return ResponseEntity.ok(new ProductRs(product));
    }

    @PostMapping
    @Override public ResponseEntity<ProductRs> save(@PathVariable Long restaurantId, @RequestBody @Valid ProductRq productRq) {
        var product = productService.save(productRq.convert(), restaurantId);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }
    @PutMapping("/{id}")
    @Override public ResponseEntity<ProductRs> update(@PathVariable Long restaurantId,
                                            @PathVariable Long id,
                                            @RequestBody @Valid ProductRq productRq) {
        var product = productService.update(productRq.convert(), restaurantId, id);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(new ProductRs(product));
    }
}
