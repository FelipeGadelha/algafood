package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.model.PaymentMethod;
import java.time.Instant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CustomJpaRepository<PaymentMethod, Long>{

    @Query("select max(updatedAt) from PaymentMethod")
    Instant getDateLastUpdate();
}
