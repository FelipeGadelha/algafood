package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;

@Repository
public class RestaurantRepositoryImpl extends RepositoryImpl<Restaurant> implements RestaurantRepository{
	
	public RestaurantRepositoryImpl() {
		super(Restaurant.class);
	}
	
}
