package br.com.portfolio.algafood.api.v1.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/v1/test")
public class TestController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@GetMapping("/kitchen/by-name")
	public List<Kitchen> findByName(@RequestParam("name") String name) {
		return kitchenRepository.findByNameContaining(name);
	}
	
	@GetMapping("/restaurant/by-tax-freight")
	public List<Restaurant> findByTaxFreight(BigDecimal taxInit, BigDecimal taxFinal) {
		return restaurantRepository.findByTaxFreightBetween(taxInit, taxFinal);
	}
	
	@GetMapping("/restaurant/by-tax")
	public List<Restaurant> find(String name, BigDecimal taxInit, BigDecimal taxFinal) {
		return restaurantRepository.find(name, taxInit, taxFinal);
	}
	
	@GetMapping("/restaurant/by-name")
	public List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId) {
//		return restaurantRepository.findByNameContainingAndKitchenId(name, kitchenId);
		return restaurantRepository.findByName(name, kitchenId);
	}
	
	@GetMapping("/restaurant/by-name-first")
	public Optional<Restaurant> findFirstRestaurantByNameContaining(String name) {
		return restaurantRepository.findFirstRestaurantByNameContaining(name);
	}
	
	@GetMapping("/restaurant/by-name-top2")
	public List<Restaurant> findTop2ByNameContaining(String name) {
		return restaurantRepository.findTop2ByNameContaining(name);
	}
	
	@GetMapping("/restaurant/exists")
	public boolean existsByName(String name) {
		return restaurantRepository.existsByName(name);
	}

	@GetMapping("/restaurant/count-kitchen")
	public int count(Long kitchenId) {
		return restaurantRepository.countByKitchenId(kitchenId);
	}

	@GetMapping("/restaurant/free-freight")
	public  List<Restaurant> findFreeFreight(String name) {
		return restaurantRepository.findWithFreeTax(name);
	}
	
	@GetMapping("/restaurant/first-register")
	public  Optional<Restaurant> firstRegister() {
		return restaurantRepository.findFirstRegister();
	}
	
	@GetMapping("/kitchen/first-register")
	public  Optional<Kitchen> kitchenFirstRegister() {
		return kitchenRepository.findFirstRegister();
	}
	
}
