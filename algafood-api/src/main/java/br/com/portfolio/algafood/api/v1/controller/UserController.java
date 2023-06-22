package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.UserControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.UserRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserUpdateRq;
import br.com.portfolio.algafood.api.v1.dto.response.UserDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import br.com.portfolio.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController implements UserControllerOpenApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    @Override public ResponseEntity<List<UserRs>> findAll() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(UserRs::new)
                .toList());
    }

    @GetMapping("/{id}")
    @Override public ResponseEntity<UserDetailRs> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(new UserDetailRs(user));
    }

    @PostMapping
    @Override public ResponseEntity<UserDetailRs> save(@Valid @RequestBody UserRq userRq) {
        var saved = userService.save(userRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserDetailRs(saved));
    }

    @PutMapping("/{id}")
    @Override public ResponseEntity<UserDetailRs> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRq userUpdateRq) {
        var update = userService.update(id, userUpdateRq.convert());
        return ResponseEntity.ok(new UserDetailRs(update));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
