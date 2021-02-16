package br.com.portfolio.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
