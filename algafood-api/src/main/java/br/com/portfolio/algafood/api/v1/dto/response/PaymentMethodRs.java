package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonView;

public class PaymentMethodRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String description;

    public PaymentMethodRs(PaymentMethod paymentMethod) {
         this.id = paymentMethod.getId();
         this.description = paymentMethod.getDescription();
    }
    public Long getId() { return id; }
    public String getDescription() { return description; }
}
