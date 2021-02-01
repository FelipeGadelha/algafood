package br.com.portfolio.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/v1/test")
public class TestController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
//	@GetMapping("/kitchen/by-name")
//	public List<Kitchen> findByName(@RequestParam("name") String name) {
//		return kitchenRepository.findByName(name);
//	}
	

}
