package br.com.portfolio.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long>{

//	List<Kitchen> findByName(String name);
	
}
