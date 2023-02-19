package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.entity.ProductImage;

public interface ProductRepositoryQueries {

    ProductImage save(ProductImage photo);
    void remove(ProductImage photo);
}
