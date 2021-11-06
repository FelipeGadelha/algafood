package br.com.portfolio.algafood.api.v1.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.RestaurantRq;
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantRs;
import br.com.portfolio.algafood.domain.exception.ValidationException;
import com.fasterxml.jackson.annotation.JsonView;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;
	private final SmartValidator smartValidator;

	@Autowired
	public RestaurantController(RestaurantService restaurantService, SmartValidator smartValidator) {
		this.restaurantService = restaurantService;
		this.smartValidator = smartValidator;
	}

	@GetMapping()
	@JsonView(View.Basic.class)
	public ResponseEntity<List<RestaurantRs>> findAll() {
		return ResponseEntity.ok(restaurantService.findAll()
				.stream()
				.map(RestaurantRs::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	@JsonView(View.Detail.class)
	public ResponseEntity<RestaurantRs> findById(@PathVariable Long id) {
		var restaurant = restaurantService.findById(id);
		return ResponseEntity.ok(new RestaurantRs(restaurant));
	}

	@PostMapping
	@JsonView(View.Detail.class)
	public ResponseEntity<RestaurantRs> save(@RequestBody @Valid RestaurantRq restaurantRq) {
		var saved = restaurantService.save(restaurantRq.convert());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new RestaurantRs(saved));
	}

	@PutMapping("/{id}")
	@JsonView(View.Detail.class)
	public ResponseEntity<RestaurantRs> update(@PathVariable @Valid Long id, @RequestBody @Valid RestaurantRq restaurantRq) {
		var updated = restaurantService.update(id, restaurantRq.convert());
		return ResponseEntity.ok(new RestaurantRs(updated));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<RestaurantRs> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
		Restaurant restaurant = restaurantService.findById(id);
		this.merge(fields, restaurant, request);
		this.validate(restaurant, "restaurant");
		var updated = restaurantService.update(id, restaurant);
		return ResponseEntity.ok(new RestaurantRs(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		restaurantService.remove(id);
		return ResponseEntity.noContent().build();
	}

	private void validate(Restaurant restaurant, String objectName) {
		var bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
		smartValidator.validate(restaurant, bindingResult);
		if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);
	}

	private void merge(Map<String, Object> fields, Restaurant restaurant, HttpServletRequest request) {
		ServletServerHttpRequest serHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurant converted = mapper.convertValue(fields, Restaurant.class);
			
			fields.forEach((propName, propValue) -> {
				Field field = ReflectionUtils.findField(Restaurant.class, propName);
				field.setAccessible(true);
				Object newValue = ReflectionUtils.getField(field, converted);
				ReflectionUtils.setField(field, restaurant, newValue);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serHttpRequest);
		}
	}

}
