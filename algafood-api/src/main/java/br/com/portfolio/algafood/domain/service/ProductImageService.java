package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.ProductImage;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ImageStorageService imageStorageService;
    private static final String MSG_PRODUCT_IMAGE_NOT_FOUND
            = "Não existe uma imagem do produto com código %d para o restaurante de código %d";

    @Autowired
    public ProductImageService(ProductRepository productRepository, ImageStorageService imageStorageService) {
        this.productRepository = productRepository;
        this.imageStorageService = imageStorageService;
    }

    @Transactional
    public ProductImage find(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PRODUCT_IMAGE_NOT_FOUND, productId, restaurantId)));
    }

    @Transactional
    public ProductImage save(ProductImage image, InputStream data) {
        var restaurantId = image.getRestaurantId();
        var productId = image.getProduct().getId();
        var fileName = imageStorageService.generateFileName(image.getFileName());
        var existingFileName = new AtomicReference<String>();
        var optional = productRepository.findPhotoById(restaurantId, productId);

        optional.ifPresent(p -> {
            existingFileName.set(p.getFileName());
            productRepository.remove(p);
        });
        image = ProductImage.builder()
                .clone(image)
                .fileName(fileName)
                .build();
        var saved = productRepository.save(image);
        productRepository.flush();

        imageStorageService.replace(
            existingFileName.get(),
            new ImageStorageService.Image(fileName, image.getContentType(), data)
        );
        return saved;
    }

    @Transactional
    public void deleteByIds(Long restaurantId, Long productId) {
        var image = this.find(restaurantId, productId);
        productRepository.remove(image);
        productRepository.flush();
        imageStorageService.remove(image.getFileName());
    }
}
