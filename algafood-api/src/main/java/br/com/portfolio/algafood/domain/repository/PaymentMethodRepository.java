package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.model.PaymentMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CustomJpaRepository<PaymentMethod, Long>{
	
}
