package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.City;
import com.fasterxml.jackson.annotation.JsonView;

public class CityRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;
    @JsonView(View.Detail.class)
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
