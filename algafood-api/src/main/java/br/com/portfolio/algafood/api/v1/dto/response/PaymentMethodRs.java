package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.PaymentMethod;
import org.springframework.hateoas.RepresentationModel;

public class PaymentMethodRs extends RepresentationModel<PaymentMethodRs> {

    private final Long id;
    private final String description;

    public PaymentMethodRs(PaymentMethod paymentMethod) {
         this.id = paymentMethod.getId();
         this.description = paymentMethod.getDescription();
    }
    public Long getId() { return id; }
    public String getDescription() { return description; }
}
