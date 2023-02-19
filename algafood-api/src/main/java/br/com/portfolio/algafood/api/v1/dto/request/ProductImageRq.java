package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.FileContentType;
import br.com.portfolio.algafood.api.validator.annotation.FileSize;
import br.com.portfolio.algafood.domain.entity.ProductImage;
import br.com.portfolio.algafood.domain.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductImageRq {

    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @NotNull private MultipartFile file;
    @NotBlank private String description;

    public ProductImageRq(MultipartFile file, String description) {
        this.file = file;
        this.description = description;
    }
    public MultipartFile getFile() { return file; }
    public ProductImage convert(Product product) {
        return ProductImage.builder()
                .fileName(file.getOriginalFilename())
                .description(description)
                .contentType(file.getContentType())
                .size(file.getSize())
                .product(product)
                .build();
    }

}