package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
public class UserRs extends RepresentationModel<UserRs>{

    @JsonView(View.Basic.class) private final Long id;
    @JsonView(View.Basic.class) private final String name;
    private final String email;

    public UserRs(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
//        user.getGroups();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
