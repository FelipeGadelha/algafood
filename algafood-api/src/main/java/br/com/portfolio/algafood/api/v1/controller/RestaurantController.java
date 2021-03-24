package br.com.portfolio.algafood.api.v1.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
		return ResponseEntity.ok(restaurantService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		return ResponseEntity.ok(restaurantService.update(id, restaurant));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Restaurant restaurant = restaurantService.findById(id);
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
//			System.err.println(propName + " = " + propValue + " = " + newValue);
			ReflectionUtils.setField(field, restaurant, newValue);
		});
	}

}
