package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.repository.StateRepository;

@Repository
public class StateRepositoryImpl extends RepositoryImpl<State> implements StateRepository{
	
	public StateRepositoryImpl() {
		super(State.class);
	}
	
}
