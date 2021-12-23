package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.ProductImage;
import br.com.portfolio.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public ProductImageService(ProductRepository productRepository, ImageStorageService imageStorageService) {
        this.productRepository = productRepository;
        this.imageStorageService = imageStorageService;
    }

    @Transactional
    public ProductImage save(ProductImage image, InputStream data) {
        var restaurantId = image.getRestaurantId();
        var productId = image.getProduct().getId();
        var fileName = imageStorageService.generateFileName(image.getFileName());
        var optional = productRepository.findPhotoById(restaurantId, productId);

        optional.ifPresent(productRepository::remove);
        image = ProductImage.builder()
                .clone(image)
                .fileName(fileName)
                .build();
        var saved = productRepository.save(image);
        productRepository.flush();
        imageStorageService.store(new ImageStorageService.Image(fileName, data));
        return saved;
    }
}
