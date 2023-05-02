package br.com.portfolio.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.model.City;

@Repository
public interface CityRepository extends CustomJpaRepository<City, Long>{

}
