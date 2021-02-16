package br.com.portfolio.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long>{

	List<Kitchen> findByNameContaining(String name);
	
}
