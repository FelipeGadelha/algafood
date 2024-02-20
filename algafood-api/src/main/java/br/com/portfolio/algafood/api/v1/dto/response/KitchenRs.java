package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.Kitchen;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Schema(name = "KitchenRs")
@Relation(collectionRelation = "kitchens")
public class KitchenRs extends RepresentationModel<KitchenRs> {

    @Schema(example = "1", description = "id")
    private final Long id;
    @Schema(example = "Italiana", description = "nome da cozinha")
    private final String name;

    public KitchenRs(Kitchen kitchen) {
        this.id = kitchen.getId();
        this.name = kitchen.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
