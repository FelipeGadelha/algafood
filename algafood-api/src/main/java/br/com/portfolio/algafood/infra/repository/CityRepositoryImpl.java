package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.repository.CityRepository;

@Repository
public class CityRepositoryImpl extends RepositoryImpl<City> implements CityRepository{
	
	public CityRepositoryImpl() {
		super(City.class);
	}
	
}
