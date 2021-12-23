package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.ProductImageRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductImageRs;
import br.com.portfolio.algafood.domain.service.ProductImageService;
import br.com.portfolio.algafood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/image")
public class RestaurantProductImageController {

    private final ProductImageService productImageService;
    private final ProductService productService;

    @Autowired
    public RestaurantProductImageController(ProductImageService productImageService, ProductService productService) {
        this.productImageService = productImageService;
        this.productService = productService;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductImageRs> update(@PathVariable Long restaurantId,
                                                 @PathVariable Long productId,
                                                 @Valid ProductImageRq imageRq) throws IOException {
        var product = productService.findById(restaurantId, productId);
        var saved = productImageService.save(imageRq.convert(product), imageRq.getFile().getInputStream());
        return ResponseEntity.ok(new ProductImageRs(saved));
    }
}
