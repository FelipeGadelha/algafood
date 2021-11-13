package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.GroupRq;
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import br.com.portfolio.algafood.domain.entity.Group;
import br.com.portfolio.algafood.domain.service.GroupService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) { this.groupService = groupService; }

    @GetMapping
    @JsonView(View.Basic.class)
    public ResponseEntity<List<GroupRs>> findAll() {
        return ResponseEntity.ok(groupService.findAll()
                .stream()
                .map(GroupRs::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @JsonView(View.Detail.class)
    public ResponseEntity<GroupRs> findById(@PathVariable Long id) {
        var group = groupService.findById(id);
        return ResponseEntity.ok(new GroupRs(group));
    }

    @PostMapping
    @JsonView(View.Detail.class)
    public ResponseEntity<GroupRs> save(@RequestBody GroupRq groupRq) {
        var group = groupService.save(groupRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GroupRs(group));
    }

    @PutMapping("/{id}")
    @JsonView(View.Detail.class)
    public ResponseEntity<GroupRs> update(@PathVariable Long id, @RequestBody GroupRq groupRq) {
        var group = groupService.update(id, groupRq.convert());
        return ResponseEntity.ok(new GroupRs(group));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { groupService.deleteById(id); }

}