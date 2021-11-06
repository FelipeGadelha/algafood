package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.State;

public class StateRq {

    private final String name;

    public StateRq(String name) {
        this.name = name;
    }

    public State convert() {
        return new State(null, name);
    }
}
