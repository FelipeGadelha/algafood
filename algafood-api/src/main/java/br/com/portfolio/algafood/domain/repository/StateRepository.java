package br.com.portfolio.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.model.State;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long>{

}
