package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.RestaurantRq;
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantRs;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.exception.BusinessException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.exception.ValidationException;
import br.com.portfolio.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public ResponseEntity<List<RestaurantRs>> findAll() {
		return ResponseEntity.ok(restaurantService.findAll()
				.stream()
				.map(RestaurantRs::new)
				.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDetailRs> findById(@PathVariable Long id) {
		var restaurant = restaurantService.findById(id);
		return ResponseEntity.ok(new RestaurantDetailRs(restaurant));
	}

	@PostMapping
	public ResponseEntity<RestaurantDetailRs> save(@RequestBody @Valid RestaurantRq restaurantRq) {
		var saved = restaurantService.save(restaurantRq.convert());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new RestaurantDetailRs(saved));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RestaurantDetailRs> update(@PathVariable @Valid Long id, @RequestBody @Valid RestaurantRq restaurantRq) {
		var updated = restaurantService.update(id, restaurantRq.convert());
		return ResponseEntity.ok(new RestaurantDetailRs(updated));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<RestaurantDetailRs> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
		Restaurant restaurant = restaurantService.findById(id);
		this.merge(fields, restaurant, request);
		this.validate(restaurant, "restaurant");
		var updated = restaurantService.update(id, restaurant);
		return ResponseEntity.ok(new RestaurantDetailRs(updated));
	}

	@PutMapping("/{id}/activate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable Long id) { restaurantService.activate(id); }

	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void massActivations(@RequestBody List<Long> ids) {
		try { restaurantService.activate(ids); }
			catch (EntityNotFoundException ex) { throw new BusinessException(ex.getMessage(), ex); }
	}

	@DeleteMapping("/{id}/inactivate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inactivate(@PathVariable Long id) { restaurantService.inactivate(id); }

	@DeleteMapping("/inactivations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void massInactivations(@RequestBody List<Long> ids) {
		try { restaurantService.inactivate(ids); }
			catch (EntityNotFoundException ex) { throw new BusinessException(ex.getMessage(), ex); }
	}

	@PutMapping("/{id}/open")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void open(@PathVariable Long id) { restaurantService.open(id); }

	@PutMapping("/{id}/close")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long id) { restaurantService.close(id); }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) { restaurantService.deleteById(id); }


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
