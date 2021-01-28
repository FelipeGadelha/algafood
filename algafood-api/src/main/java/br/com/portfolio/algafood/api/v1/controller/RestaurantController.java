package br.com.portfolio.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.infra.repository.RestaurantRepositoryImpl;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepositoryImpl repository;
	
	@GetMapping()
	public List<Restaurant> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable Long id) {
		return repository.find(id);
	}
	
//	@PostMapping
//	public Restaurant save(@RequestBody Restaurant restaurant) {
//		System.err.println(restaurant);
//		return repository.save(restaurant);
//	}
	
	
	
	
	

}
