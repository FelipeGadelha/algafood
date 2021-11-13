package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Group;
import com.fasterxml.jackson.annotation.JsonView;

public class GroupRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;

    public GroupRs(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
