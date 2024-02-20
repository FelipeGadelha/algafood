package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.State;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
public class StateRs extends RepresentationModel<StateRs> {

    private final Long id;
    private final String name;

    public StateRs(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
