package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantPaymentMethodController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodRs>> findAll(@PathVariable Long restaurantId) {
        final var restaurant = restaurantService.findById(restaurantId);
        return ResponseEntity.ok(restaurant.getPaymentMethod()
                .stream()
                .map(PaymentMethodRs::new)
                .collect(Collectors.toList()));
    }

    @DeleteMapping
    public void deletePaymentMethod(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantService.deletePaymentMethod(restaurantId, id);
    }


}
