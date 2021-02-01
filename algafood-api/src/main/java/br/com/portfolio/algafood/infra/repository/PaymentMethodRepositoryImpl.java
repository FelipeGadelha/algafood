package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import br.com.portfolio.algafood.domain.repository.PaymentMethodRepository;

@Repository
public class PaymentMethodRepositoryImpl extends RepositoryImpl<PaymentMethod> implements PaymentMethodRepository{
	
	public PaymentMethodRepositoryImpl() {
		super(PaymentMethod.class);
	}
	
}
