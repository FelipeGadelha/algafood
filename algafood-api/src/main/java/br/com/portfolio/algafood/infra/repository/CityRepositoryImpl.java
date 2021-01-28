package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.repository.CityRepository;

@Component
public class CityRepositoryImpl extends RepositoryImpl<City> implements CityRepository{
	
	public CityRepositoryImpl() {
		super(City.class);
	}
	
}
