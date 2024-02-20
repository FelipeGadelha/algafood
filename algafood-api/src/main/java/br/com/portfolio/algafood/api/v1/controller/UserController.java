package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.UserControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.PasswordRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserUpdateRq;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import br.com.portfolio.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/users")
public class UserController implements UserControllerOpenApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    @Override public ResponseEntity<CollectionModel<UserRs>> findAll() {
        var users = userService.findAll()
            .stream()
            .map(UserRs::new)
            .toList();
        users.forEach(this::addLinks);
        var result = CollectionModel.of(users);
        result.add(linkTo(methodOn(this.getClass()).findAll()).withSelfRel());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Override public ResponseEntity<UserRs> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        var userRs = new UserRs(user);
        this.addLinks(userRs);
        return ResponseEntity.ok(userRs);
    }

    @PostMapping
    @Override public ResponseEntity<UserRs> save(@Valid @RequestBody UserRq userRq) {
        var saved = userService.save(userRq.convert());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserRs(saved));
    }

    @PutMapping("/{id}")
    @Override public ResponseEntity<UserRs> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRq userUpdateRq) {
        var update = userService.update(id, userUpdateRq.convert());
        return ResponseEntity.ok(new UserRs(update));
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void passwordUpdate(@PathVariable Long id, PasswordRq request) {
        userService.passwordUpdate(id, request.password(), request.newPassword());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private void addLinks(UserRs userRs) {
        userRs.add(
            linkTo(methodOn(this.getClass()).findAll()).withRel(LinkRelation.of("findAll")),
            linkTo(methodOn(this.getClass()).findById(userRs.getId())).withSelfRel()
        );
//        userRs.getState().add(
//            linkTo(methodOn(StateController.class).findAll()).withRel(LinkRelation.of("findAll")),
//            linkTo(methodOn(StateController.class).findById(userRs.getId())).withSelfRel()
//        );
    }
}
