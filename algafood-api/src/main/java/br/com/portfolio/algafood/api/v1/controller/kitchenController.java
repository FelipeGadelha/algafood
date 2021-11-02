package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController {

	private final KitchenService kitchenService;

	@Autowired
	public kitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(kitchenService.findAll());
	}

	@GetMapping("/{kitchenId}")
	public ResponseEntity<?> findById(@PathVariable("kitchenId") Long id) {
		return ResponseEntity.ok(kitchenService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid Kitchen kitchen) {
		return new ResponseEntity<>(kitchenService.save(kitchen), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {
		return ResponseEntity.ok(kitchenService.update(id, kitchen));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		kitchenService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
