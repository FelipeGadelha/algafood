package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.ProductImageRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductImageRs;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.service.ImageStorageService;
import br.com.portfolio.algafood.domain.service.ProductImageService;
import br.com.portfolio.algafood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/image")
public class RestaurantProductImageController {

    private final ProductImageService productImageService;
    private final ProductService productService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public RestaurantProductImageController(
        ProductImageService productImageService,
        ProductService productService,
        ImageStorageService imageStorageService
    ) {
        this.productImageService = productImageService;
        this.productService = productService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductImageRs> find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        final var image = productImageService.find(restaurantId, productId);
        return ResponseEntity.ok(new ProductImageRs(image));
    }
    @GetMapping
    public ResponseEntity<?> findImage(
        @PathVariable Long restaurantId,
        @PathVariable Long productId,
        @RequestHeader String accept
    ) throws HttpMediaTypeNotAcceptableException {
        try {
            final var image = productImageService.find(restaurantId, productId);

            var type= MediaType.parseMediaType(image.getContentType());
            var typesAccept = MediaType.parseMediaTypes(accept);

            mediaTypeCompatibilityCheck(type, typesAccept);

            var imageRecover = imageStorageService.recover(image.getFileName());

            return imageRecover.url()
                .isPresent()
                ? ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, imageRecover.url().orElseThrow())
                    .build()
                : ResponseEntity.ok()
                    .contentType(type)
                    .body(new InputStreamResource(imageRecover.inputStream().orElseThrow()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductImageRs> update(
        @PathVariable Long restaurantId,
        @PathVariable Long productId,
        @Valid ProductImageRq imageRq
    ) throws IOException {
        var product = productService.findById(restaurantId, productId);
        var saved = productImageService
            .save(imageRq.convert(product), imageRq.getFile().getInputStream());
        return ResponseEntity.ok(new ProductImageRs(saved));
    }
    private void mediaTypeCompatibilityCheck(
        MediaType type,
        List<MediaType> typesAccept
    ) throws HttpMediaTypeNotAcceptableException {
        boolean compatible = typesAccept.stream().anyMatch(type::isCompatibleWith);
        if (!compatible) throw new HttpMediaTypeNotAcceptableException(typesAccept);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        productImageService.deleteByIds(restaurantId, productId);
    }
}
