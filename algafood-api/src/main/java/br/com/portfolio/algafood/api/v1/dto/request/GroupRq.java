package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Group;
import com.fasterxml.jackson.annotation.JsonCreator;

public class GroupRq {

    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GroupRq(String name) { this.name = name; }

    public Group convert() { return new Group(null, name, null); }
}
