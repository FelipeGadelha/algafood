package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.GroupControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.GroupRq;
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import br.com.portfolio.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/groups")
public class GroupController implements GroupControllerOpenApi {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) { this.groupService = groupService; }

    @GetMapping
    @Override public ResponseEntity<List<GroupRs>> findAll() {
        return ResponseEntity.ok(groupService.findAll()
                .stream()
                .map(GroupRs::new)
                .toList());
    }

    @GetMapping("/{id}")
    @Override public ResponseEntity<GroupRs> findById(@PathVariable Long id) {
        var group = groupService.findById(id);
        return ResponseEntity.ok(new GroupRs(group));
    }

    @PostMapping
    @Override public ResponseEntity<GroupRs> save(@RequestBody GroupRq groupRq) {
        var group = groupService.save(groupRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GroupRs(group));
    }

    @PutMapping("/{id}")
    @Override public ResponseEntity<GroupRs> update(@PathVariable Long id, @RequestBody @Valid GroupRq groupRq) {
        var group = groupService.update(id, groupRq.convert());
        return ResponseEntity.ok(new GroupRs(group));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void deleteById(@PathVariable Long id) { groupService.deleteById(id); }
}
