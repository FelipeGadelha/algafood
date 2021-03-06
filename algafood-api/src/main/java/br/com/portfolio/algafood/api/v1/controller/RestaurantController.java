package br.com.portfolio.algafood.api.v1.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping()
	public List<Restaurant> findAll() {
		return restaurantService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Restaurant entity = restaurantService.findById(id);
		if (Objects.nonNull(entity))
			return ResponseEntity.ok(entity);
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		try {
			return ResponseEntity.ok(restaurantService.update(
					new Restaurant(id, 
							restaurant.getName(), 
							restaurant.getTaxFreight(), 
							restaurant.getKitchen(), 
							restaurant.getPaymentMethod(), 
							restaurant.getAddress(), 
							restaurant.getProducts()
							)));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Restaurant restaurant = restaurantService.findById(id);
		if (Objects.isNull(restaurant)) return ResponseEntity.notFound().build();
		merge(fields, restaurant);
		return update(id, restaurant);
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		restaurantService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	private void merge(Map<String, Object> fields, Restaurant restaurant) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurant converted = mapper.convertValue(fields, Restaurant.class);
		
		fields.forEach((propName, propValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propName);
			field.setAccessible(true);
			Object newValue = ReflectionUtils.getField(field, converted);
			System.out.println(propName + " = " + propValue + " = " + newValue);
			ReflectionUtils.setField(field, restaurant, newValue);
		});
	}

}
