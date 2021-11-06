package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.KitchenRq;
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.service.KitchenService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController {

	private final KitchenService kitchenService;

	@Autowired
	public kitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping
	@JsonView(View.Basic.class)
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(kitchenService.findAll()
				.stream()
				.map(KitchenRs::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{kitchenId}")
	@JsonView(View.Detail.class)
	public ResponseEntity<?> findById(@PathVariable("kitchenId") Long id) {
		return ResponseEntity.ok(new KitchenRs(kitchenService.findById(id)));
	}

	@PostMapping
	@JsonView(View.Detail.class)
	public ResponseEntity<?> save(@RequestBody @Valid KitchenRq kitchenRq) {
		Kitchen saved = kitchenService.save(kitchenRq.convert());
		return new ResponseEntity<>(new KitchenRs(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@JsonView(View.Detail.class)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid KitchenRq kitchenRq) {
		Kitchen update = kitchenService.update(id, kitchenRq.convert());
		return ResponseEntity.ok(new KitchenRs(update));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		kitchenService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
