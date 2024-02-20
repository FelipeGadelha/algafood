package br.com.portfolio.algafood.infra.repository;

import br.com.portfolio.algafood.domain.model.ProductImage;
import br.com.portfolio.algafood.domain.repository.ProductRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    private final EntityManager manager;

    @Autowired public ProductRepositoryImpl(EntityManager manager) { this.manager = manager; }

    @Transactional
    @Override
    public ProductImage save(ProductImage photo) { return manager.merge(photo); }

    @Transactional
    @Override
    public void remove(ProductImage photo) { manager.remove(photo); }
}
