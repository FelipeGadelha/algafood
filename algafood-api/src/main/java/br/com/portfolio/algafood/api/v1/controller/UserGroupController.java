package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.domain.service.UserGroupService;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users/{userId}/groups")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupRs>> findAll(@PathVariable Long userId) {
        return ResponseEntity.ok(userGroupService.findById(userId)
                .stream()
                .map(GroupRs::new)
                .collect(Collectors.toList()));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void connect(@PathVariable Long userId, @PathVariable Long id) {
        userGroupService.connect(userId, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disconnect(@PathVariable Long userId, @PathVariable Long id) {
        userGroupService.disconnect(userId, id);
    }
}
