package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Group;
import br.com.portfolio.algafood.domain.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class GroupPermissionService {

    private final GroupService groupService;
    private final PermissionService permissionService;

    @Autowired
    public GroupPermissionService(GroupService groupService, PermissionService permissionService) {
        this.groupService = groupService;
        this.permissionService = permissionService;
    }

    @Transactional
    public Set<Permission> findAll(Long groupId) { return groupService.findById(groupId).getPermissions(); }

    @Transactional
    public void connect(Long groupId, Long id) {
        var group = groupService.findById(groupId);
        var permission = permissionService.findById(id);
        Group.builder()
                .clone(group)
                .addPermissions(permission)
                .build();
    }
    @Transactional
    public void disconnect(Long groupId, Long id) {
        var group = groupService.findById(groupId);
        var permission = permissionService.findById(id);
        Group.builder()
                .clone(group)
                .removePermissions(permission)
                .build();
    }
}
