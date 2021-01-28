package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.repository.StateRepository;

@Component
public class StateRepositoryImpl extends RepositoryImpl<State> implements StateRepository{
	
	public StateRepositoryImpl() {
		super(State.class);
	}
	
}
