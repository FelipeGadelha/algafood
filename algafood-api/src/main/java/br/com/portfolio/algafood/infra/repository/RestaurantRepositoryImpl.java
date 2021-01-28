package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl extends RepositoryImpl<Restaurant> implements RestaurantRepository{
	
	public RestaurantRepositoryImpl() {
		super(Restaurant.class);
	}
	
}
