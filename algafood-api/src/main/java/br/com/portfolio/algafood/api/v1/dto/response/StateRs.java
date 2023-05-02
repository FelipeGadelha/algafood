package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.State;

public class StateRs {

    private final Long id;
    private final String name;

    public StateRs(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
