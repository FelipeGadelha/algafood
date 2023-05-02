package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.GroupRq;
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import br.com.portfolio.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) { this.groupService = groupService; }

    @GetMapping
    public ResponseEntity<List<GroupRs>> findAll() {
        return ResponseEntity.ok(groupService.findAll()
                .stream()
                .map(GroupRs::new)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupRs> findById(@PathVariable Long id) {
        var group = groupService.findById(id);
        return ResponseEntity.ok(new GroupRs(group));
    }

    @PostMapping
    public ResponseEntity<GroupRs> save(@RequestBody GroupRq groupRq) {
        var group = groupService.save(groupRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GroupRs(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupRs> update(@PathVariable Long id, @RequestBody @Valid GroupRq groupRq) {
        var group = groupService.update(id, groupRq.convert());
        return ResponseEntity.ok(new GroupRs(group));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) { groupService.deleteById(id); }

}
