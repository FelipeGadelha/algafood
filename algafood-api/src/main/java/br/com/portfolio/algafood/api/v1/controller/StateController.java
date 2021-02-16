package br.com.portfolio.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.repository.StateRepository;

@RestController
@RequestMapping("/v1/states")
public class StateController {
	
	@Autowired
	private StateRepository repository;
	
	@GetMapping()
	public List<State> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public State findById(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
//	@PostMapping
//	public Restaurant save(@RequestBody Restaurant restaurant) {
//		System.err.println(restaurant);
//		return repository.save(restaurant);
//	}
	
	
	
	
	

}
