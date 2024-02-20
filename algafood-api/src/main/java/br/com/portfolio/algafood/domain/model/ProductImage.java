package br.com.portfolio.algafood.domain.model;


import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id @Column(name = "product_id")
    private Long id;
    private String fileName;
    private String description;
    private String contentType;
    private Long size;
    @OneToOne(fetch = FetchType.LAZY) @MapsId private Product product;

    @Deprecated public ProductImage() { }

    private ProductImage(Builder builder) {
        this.fileName = builder.fileName;
        this.description = builder.description;
        this.contentType = builder.contentType;
        this.size = builder.size;
        this.product = builder.product;
    }
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String fileName;
        private String description;
        private String contentType;
        private Long size;
        private Product product;
        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
        public Builder size(Long size) {
            this.size = size;
            return this;
        }
        public Builder product(Product product) {
            this.product = product;
            return this;
        }
        public Builder copy(ProductImage productImage) {
            this.id = (Objects.isNull(id)) ? productImage.id : this.id;
            this.fileName = productImage.fileName;
            this.description = productImage.description;
            this.contentType = productImage.contentType;
            this.size = productImage.size;
            this.product = (Objects.isNull(product)) ? productImage.product : this.product;
            return this;
        }
        public Builder clone(ProductImage productImage) {
            this.id = productImage.id;
            this.fileName = productImage.fileName;
            this.description = productImage.description;
            this.contentType = productImage.contentType;
            this.size = productImage.size;
            this.product = productImage.product;
            return this;
        }
        public ProductImage build() { return new ProductImage(this); }

    }
    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public String getDescription() { return description; }
    public String getContentType() { return contentType; }
    public Long getSize() { return size; }
    public Product getProduct() { return product; }
    public Long getRestaurantId() {
        if (getProduct() != null) return getProduct().getRestaurant().getId();
        return null;
    }
}
