package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.UserRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserUpdateRq;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import br.com.portfolio.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity<List<UserRs>> findAll() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserRs::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRs> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(new UserRs(user));
    }

    @PostMapping
    public ResponseEntity<UserRs> save(@Valid @RequestBody UserRq userRq) {
        var saved = userService.save(userRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserRs(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRs> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRq userUpdateRq) {
        var update = userService.update(id, userUpdateRq.convert());
        return ResponseEntity.ok(new UserRs(update));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
