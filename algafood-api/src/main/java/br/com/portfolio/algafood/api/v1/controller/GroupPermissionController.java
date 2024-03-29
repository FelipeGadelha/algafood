package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.GroupPermissionControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import br.com.portfolio.algafood.domain.service.GroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/groups/{groupId}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    private final GroupPermissionService groupPermissionService;

    @Autowired
    public GroupPermissionController(GroupPermissionService groupPermissionService){
        this.groupPermissionService = groupPermissionService;
    }

    @GetMapping
    @Override public ResponseEntity<Set<PermissionRs>> findAll(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupPermissionService.findAll(groupId).stream()
                .map(PermissionRs::new)
                .collect(Collectors.toSet()));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void connect(@PathVariable Long groupId, @PathVariable Long id) {
        groupPermissionService.connect(groupId, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void disconnect(@PathVariable Long groupId, @PathVariable Long id) {
        groupPermissionService.disconnect(groupId, id);
    }
}
