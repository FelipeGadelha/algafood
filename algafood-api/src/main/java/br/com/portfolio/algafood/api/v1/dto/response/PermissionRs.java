package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Permission;
import com.fasterxml.jackson.annotation.JsonView;

public class PermissionRs {

    @JsonView(View.Basic.class) private final Long id;
    @JsonView(View.Basic.class) private final String name;
    private final String description;

    public PermissionRs(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.description = permission.getDescription();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
