package br.com.portfolio.algafood.api.v1.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.service.KitchenService;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController {

	private final KitchenService kitchenService;

	@Autowired
	public kitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Kitchen> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{kitchenId}")
	public ResponseEntity<?> findById(@PathVariable("kitchenId") Long id) {
		try {
			return ResponseEntity.ok(kitchenService.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
//			updated.setName(kitchen.getName());
//			BeanUtils.copyProperties(kitchen, entity, "id");
		try {
			Kitchen save = kitchenService.update(new Kitchen(id, kitchen.getName()));
			return ResponseEntity.ok(save);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			kitchenService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
