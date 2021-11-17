package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonView;

public class UserRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;
    @JsonView({View.Basic.class, View.Detail.class})
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
