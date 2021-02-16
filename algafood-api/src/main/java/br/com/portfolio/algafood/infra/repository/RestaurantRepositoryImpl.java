package br.com.portfolio.algafood.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;
import br.com.portfolio.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurant> find(String name, BigDecimal taxFreightInit, BigDecimal taxFreightFinal){
		
		var jpql = "from Restaurant where name like :name "
				+ "and taxFreight between :taxFreightInit and :taxFreightFinal";
		
		return manager.createQuery(jpql, Restaurant.class)
				.setParameter("name", "%"+ name +"%")
				.setParameter("taxFreightInit", taxFreightInit)
				.setParameter("taxFreightFinal", taxFreightFinal)
				.getResultList();
	}
	
	
	
}
