package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl extends RepositoryImpl<Kitchen> implements KitchenRepository {

	public KitchenRepositoryImpl() {
		super(Kitchen.class);
	}

}
