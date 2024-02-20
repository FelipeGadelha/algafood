package br.com.portfolio.algafood.api.hateoas;

import br.com.portfolio.algafood.api.v1.controller.kitchenController;
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import br.com.portfolio.algafood.domain.model.Kitchen;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenRs> {

    public KitchenAssembler() {
        super(kitchenController.class, KitchenRs.class);
    }

    @Override
    public KitchenRs toModel(Kitchen entity) {
        return new KitchenRs(entity);
    }
}
