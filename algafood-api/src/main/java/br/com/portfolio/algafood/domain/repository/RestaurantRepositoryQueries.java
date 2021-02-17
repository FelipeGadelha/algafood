package br.com.portfolio.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.portfolio.algafood.domain.entity.Restaurant;

public interface RestaurantRepositoryQueries {

	public List<Restaurant> find(String name, BigDecimal taxFreightInit, BigDecimal taxFreightFinal);
	
	public List<Restaurant> findWithFreeTax(String name);
	
}