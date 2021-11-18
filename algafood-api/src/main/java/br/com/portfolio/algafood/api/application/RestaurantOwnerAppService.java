package br.com.portfolio.algafood.api.application;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.entity.User;
import br.com.portfolio.algafood.domain.service.RestaurantService;
import br.com.portfolio.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestaurantOwnerAppService {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public RestaurantOwnerAppService(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }
    @Transactional
    public Set<User> findAllOwner(Long restaurantId) {
        return restaurantService.findById(restaurantId).getOwners();
    }
    @Transactional
    public void connectOwner(Long id, Long userId) {
        var restaurant = this.restaurantService.findById(id);
        var owner = this.userService.findById(userId);
        Restaurant.builder()
                .clone(restaurant)
                .addOwner(owner)
                .build();
    }
    @Transactional
    public void disconnectOwner(Long id, Long userId) {
        var restaurant = this.restaurantService.findById(id);
        var owner = this.userService.findById(userId);
        Restaurant.builder()
                .clone(restaurant)
                .removeOwner(owner)
                .build();
    }

}
