package br.com.portfolio.algafood.domain.service;

import java.util.List;

import br.com.portfolio.algafood.domain.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.model.Restaurant;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {

	private static final String MSG_RESTAURANT_NOT_FOUND = "Não existe Restaurante com o ID %d";
	private final RestaurantRepository restaurantRepository;
	private final KitchenService kitchenService;
	private final CityService cityService;
	private final PaymentMethodService paymentMethodService;
	
	@Autowired
	public RestaurantService(
		RestaurantRepository restaurantRepository,
	 	KitchenService kitchenService,
	 	CityService cityService,
	 	PaymentMethodService paymentMethodService
	) {
		this.restaurantRepository = restaurantRepository;
		this.kitchenService = kitchenService;
		this.cityService = cityService;
		this.paymentMethodService = paymentMethodService;
	}

	@Transactional
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	@Transactional
	public Restaurant findById(Long id) {
		return restaurantRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, id)));
	}

	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Long KitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.findById(KitchenId);
		final var city = cityService.findById(restaurant.getCityId());
		restaurant = Restaurant.builder()
			.clone(restaurant)
			.kitchen(kitchen)
			.address(a -> Address.builder()
				.clone(a)
				.city(city)
				.build()
			).build();
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public Restaurant update(Long id, Restaurant updated) {
		var restaurant = this.findById(id);
		restaurant = Restaurant.builder()
			.clone(restaurant)
			.copy(updated)
			.build();
		return this.save(restaurant);
	}

	public Restaurant patch(Restaurant restaurant) {
		System.out.println(restaurant);
		return null;
	}
	@Transactional public void activate(Long id) { this.findById(id).activate(); }
	@Transactional public void activate(List<Long> ids) { ids.forEach(this::activate); }
	@Transactional public void inactivate(Long id) { this.findById(id).inactivate(); }
	@Transactional public void inactivate(List<Long> ids) { ids.forEach(this::inactivate); }
	@Transactional public void open(Long id) { this.findById(id); }
	@Transactional public void close(Long id) { this.findById(id); }

	@Transactional
	public void deleteById(Long id) {
		try {
			restaurantRepository.deleteById(id);
			restaurantRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Restaurante com o ID %d não existe", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Restaurante com o ID %d não pode ser removido, pois esta em uso", id));
		}
	}
}
