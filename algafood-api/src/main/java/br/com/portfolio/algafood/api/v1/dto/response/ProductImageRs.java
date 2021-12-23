package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.entity.ProductImage;

public class ProductImageRs {
    private String fileName;
    private String description;
    private String contentType;
    private Long size;

    public ProductImageRs(ProductImage photo) {
        this.fileName = photo.getFileName();
        this.description = photo.getDescription();
        this.contentType = photo.getContentType();
        this.size = photo.getSize();
    }
    public String getFileName() { return fileName; }
    public String getDescription() { return description; }
    public String getContentType() { return contentType; }
    public Long getSize() { return size; }
}
