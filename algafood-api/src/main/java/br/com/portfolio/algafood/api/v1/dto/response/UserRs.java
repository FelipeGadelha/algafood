package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.entity.User;

public class UserRs {

    private final Long id;
    private final String name;

    public UserRs(User user) {
        this.id = user.getId();
        this.name = user.getName();
//        user.getGroups();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
