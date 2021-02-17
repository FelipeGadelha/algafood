package br.com.portfolio.algafood.domain.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final KitchenRepository kitchenRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepository, KitchenRepository kitchenRepository) {
		this.restaurantRepository = restaurantRepository;
		this.kitchenRepository = kitchenRepository;
	}
	
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant findById(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Não existe Restaurante com o ID %d", id)));
	}

	public Restaurant save(Restaurant restaurant) {
		Long id = restaurant.getKitchen().getId();
		Optional<Kitchen> entity = kitchenRepository.findById(id);
		if (entity.isEmpty()) throw new EntityNotFoundException(String.format("Não existe Cozinha com o ID %d", id));
		restaurant.setKitchen(entity.get());
		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Restaurant restaurant) {
		if (Objects.isNull(restaurantRepository.findById(restaurant.getId()).get())) {
			throw new EntityNotFoundException(String.format("Não existe Restaurante com o ID %d", restaurant.getId()));
		}
		Long id = restaurant.getKitchen().getId();
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		if (kitchen.isEmpty()) throw new EntityNotFoundException(String.format("Não existe Cozinha com o ID %d", id));
		restaurant.setKitchen(kitchen.get());
		return restaurantRepository.save(restaurant);
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
