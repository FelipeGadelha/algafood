package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.RestaurantOwnerControllerOpenApi;
import br.com.portfolio.algafood.domain.service.RestaurantOwnerService;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/owners")
public class RestaurantOwnerController implements RestaurantOwnerControllerOpenApi {

    private final RestaurantOwnerService restaurantOwnerService;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    @GetMapping
    @Override public ResponseEntity<CollectionModel<UserRs>> findAllOwner(@PathVariable Long restaurantId) {
        var owners = restaurantOwnerService.findAllOwner(restaurantId).stream()
            .map(UserRs::new)
            .toList();
//            .collect(Collectors.toSet());
        owners.forEach(this::addLinks);
        var result = CollectionModel.of(owners);
        result.add(linkTo(methodOn(this.getClass()).findAllOwner(restaurantId)).withSelfRel());
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void connectOwner(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantOwnerService.connectOwner(restaurantId, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void disconnectOwner(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantOwnerService.disconnectOwner(restaurantId, id);
    }
    private void addLinks(UserRs userRs) {
        userRs.add(
            linkTo(methodOn(UserController.class).findAll()).withRel(LinkRelation.of("findAll")),
            linkTo(methodOn(UserController.class).findById(userRs.getId())).withSelfRel()
        );
//        userRs.getState().add(
//            linkTo(methodOn(StateController.class).findAll()).withRel(LinkRelation.of("findAll")),
//            linkTo(methodOn(StateController.class).findById(userRs.getId())).withSelfRel()
//        );
    }

}
