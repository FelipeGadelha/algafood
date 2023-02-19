package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Group;
import br.com.portfolio.algafood.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserGroupService {

    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public UserGroupService(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Transactional
    public List<Group> findById(Long userId) { return userService.findById(userId).getGroups(); }

    @Transactional
    public void connect(Long userId, Long id) {
        var user = userService.findById(userId);
        var group = groupService.findById(id);
        User.builder()
                .clone(user)
                .addGroup(group)
                .build();
    }
    @Transactional
    public void disconnect(Long userId, Long id) {
        var user = userService.findById(userId);
        var group = groupService.findById(id);
        User.builder()
                .clone(user)
                .removeGroup(group)
                .build();
    }
}
