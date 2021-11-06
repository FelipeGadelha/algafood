package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.State;
import com.fasterxml.jackson.annotation.JsonView;

public class StateRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;

    public StateRs(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
