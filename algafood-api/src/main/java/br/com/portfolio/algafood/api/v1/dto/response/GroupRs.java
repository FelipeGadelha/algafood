package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.Group;

public class GroupRs {

    private final Long id;
    private final String name;

    public GroupRs(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
