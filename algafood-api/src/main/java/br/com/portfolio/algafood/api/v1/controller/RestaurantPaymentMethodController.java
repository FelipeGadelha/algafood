package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import br.com.portfolio.algafood.domain.service.RestaurantPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    private final RestaurantPaymentMethodService restaurantPaymentMethodService;

    @Autowired
    public RestaurantPaymentMethodController(RestaurantPaymentMethodService restaurantPaymentMethodService) {
        this.restaurantPaymentMethodService = restaurantPaymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodRs>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantPaymentMethodService.findAll(restaurantId)
                .stream()
                .map(PaymentMethodRs::new)
                .toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentMethod(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantPaymentMethodService.deletePaymentMethod(restaurantId, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentMethod(@PathVariable Long restaurantId, @PathVariable Long id) {
        restaurantPaymentMethodService.addPaymentMethod(restaurantId, id);
    }

}
