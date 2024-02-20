package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.City;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
public class CityRs extends RepresentationModel<CityRs> {

    @JsonView(View.Basic.class)
    private final Long id;
    @JsonView(View.Basic.class)
    private final String name;
    private final StateRs state;

    public CityRs(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = new StateRs(city.getState());
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public StateRs getState() { return state; }
}
