package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import br.com.portfolio.algafood.api.application.GroupPermissionAppService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/groups/{groupId}/permissions")
public class GroupPermissionController {

    private final GroupPermissionAppService groupPermissionAppService;

    @Autowired
    public GroupPermissionController(GroupPermissionAppService groupPermissionAppService){
        this.groupPermissionAppService = groupPermissionAppService;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    public ResponseEntity<Set<PermissionRs>> findAll(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupPermissionAppService.findAll(groupId).stream()
                .map(PermissionRs::new)
                .collect(Collectors.toSet()));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void connect(@PathVariable Long groupId, @PathVariable Long id) {
        groupPermissionAppService.connect(groupId, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disconnect(@PathVariable Long groupId, @PathVariable Long id) {
        groupPermissionAppService.disconnect(groupId, id);
    }


}
