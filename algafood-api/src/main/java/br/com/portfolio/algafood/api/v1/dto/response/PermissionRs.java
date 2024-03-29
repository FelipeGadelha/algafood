package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.Permission;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;

public class PermissionRs extends RepresentationModel<PermissionRs> {

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
