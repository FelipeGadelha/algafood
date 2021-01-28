package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import br.com.portfolio.algafood.domain.repository.PaymentMethodRepository;

@Component
public class PaymentMethodRepositoryImpl extends RepositoryImpl<PaymentMethod> implements PaymentMethodRepository{
	
	public PaymentMethodRepositoryImpl() {
		super(PaymentMethod.class);
	}
	
}
