package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.PaymentMethod;
import br.com.portfolio.algafood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestaurantPaymentMethodService {

    private final RestaurantService restaurantService;
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public RestaurantPaymentMethodService(RestaurantService restaurantService, PaymentMethodService paymentMethodService) {
        this.restaurantService = restaurantService;
        this.paymentMethodService = paymentMethodService;
    }
    @Transactional
    public Set<PaymentMethod> findAll(Long restaurantId) {
        return restaurantService.findById(restaurantId).getPaymentMethod();
    }
    @Transactional
    public void deletePaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = restaurantService.findById(restaurantId);
        var paymentMethod = paymentMethodService.findById(paymentMethodId);
        Restaurant.builder()
                .clone(restaurant)
                .removePaymentMethod(paymentMethod)
                .build();
    }
    @Transactional
    public void addPaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = restaurantService.findById(restaurantId);
        var paymentMethod = paymentMethodService.findById(paymentMethodId);
        Restaurant.builder()
                .clone(restaurant)
                .addPaymentMethod(paymentMethod)
                .build();
    }

}
