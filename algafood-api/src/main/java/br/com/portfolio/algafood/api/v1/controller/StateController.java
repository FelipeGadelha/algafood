package br.com.portfolio.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.service.StateService;

@RestController
@RequestMapping("/v1/states")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	public StateController(StateService stateService) {
		this.stateService = stateService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(stateService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(stateService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody State state) {
		return new ResponseEntity<>(stateService.save(state), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody State state) {
		return ResponseEntity.ok(stateService.update(id, state));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		stateService.deleteById(id);
	}
	

}
