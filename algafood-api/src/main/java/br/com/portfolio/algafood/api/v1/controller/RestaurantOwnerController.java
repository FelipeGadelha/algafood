package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.domain.service.RestaurantOwnerService;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/owners")
public class RestaurantOwnerController {

    private final RestaurantOwnerService restaurantOwnerService;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerService restaurantOwnerService) {
        this.restaurantOwnerService = restaurantOwnerService;
    }

    @GetMapping
    public ResponseEntity<Set<UserRs>> findAllOwner(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantOwnerService.findAllOwner(restaurantId).stream()
                .map(UserRs::new)
                .collect(Collectors.toSet()));
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void connectOwner(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantOwnerService.connectOwner(restaurantId, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disconnectOwner(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantOwnerService.disconnectOwner(restaurantId, id);
    }

}
