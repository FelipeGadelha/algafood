package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.FileContentType;
import br.com.portfolio.algafood.api.validator.annotation.FileSize;
import br.com.portfolio.algafood.domain.model.ProductImage;
import br.com.portfolio.algafood.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "ProductImageRq")
public class ProductImageRq {

    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @NotNull private MultipartFile file;
    @Schema(name = "description", description = "descrição da imagem do produto do restaurante")
    @NotBlank private String description;

    public ProductImageRq(MultipartFile file, String description) {
        this.file = file;
        this.description = description;
    }
    public MultipartFile getFile() { return file; }
    public String getDescription() { return description; }

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