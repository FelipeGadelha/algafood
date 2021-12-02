package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonView;

public class UserDetailRs {

    @JsonView(View.Basic.class) private final Long id;
    @JsonView(View.Basic.class) private final String name;
    private final String email;

    public UserDetailRs(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
//        user.getGroups();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
