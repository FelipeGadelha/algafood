package br.com.portfolio.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Kitchen;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long>{

	List<Kitchen> findByNameContaining(String name);
	
}
