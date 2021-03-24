package br.com.portfolio.algafood.domain.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private static final String MSG_RESTAURANT_NOT_FOUND = "Não existe Restaurante com o ID %d";
	
	private final RestaurantRepository restaurantRepository;
	private final KitchenService kitchenService;
	private final CityService cityService;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepository, KitchenService kitchenService, CityService cityService) {
		this.restaurantRepository = restaurantRepository;
		this.kitchenService = kitchenService;
		this.cityService = cityService;
	}
	
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant findById(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id)));
	}

	public Restaurant save(Restaurant restaurant) {
		Long KitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.findById(KitchenId);
		restaurant.setKitchen(kitchen);
		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Long id, Restaurant updated) {
		Restaurant restaurant = this.findById(id);
		BeanUtils.copyProperties(updated, restaurant, "id", "paymentMethod", "address");
		return this.save(restaurant);
	}
	
	public Restaurant patch(Restaurant restaurant) {
		System.out.println(restaurant);
		return null;
	}

	public void remove(Long id) {
		try {
			restaurantRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Restaurante com o ID %d não existe", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Restaurante com o ID  %d não pode ser removida, pois esta em uso", id));
		}
	}

}
