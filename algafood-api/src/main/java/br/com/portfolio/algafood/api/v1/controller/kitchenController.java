package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.kitchenControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.KitchenRq;
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController implements kitchenControllerOpenApi {

	private final KitchenService kitchenService;

	@Autowired
	public kitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping
	@Override public ResponseEntity<Page<KitchenRs>> findAll(Pageable pageable) {
		return ResponseEntity.ok(kitchenService.findAll(pageable).map(KitchenRs::new));
	}

	@GetMapping("/{kitchenId}")
	@Override public ResponseEntity<KitchenRs> findById(@PathVariable("kitchenId") Long id) {
		return ResponseEntity.ok(new KitchenRs(kitchenService.findById(id)));
	}

	@PostMapping
	@Override public ResponseEntity<KitchenRs> save(@RequestBody @Valid KitchenRq kitchenRq) {
		Kitchen saved = kitchenService.save(kitchenRq.convert());
		return new ResponseEntity<>(new KitchenRs(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Override public ResponseEntity<KitchenRs> update(@PathVariable Long id, @RequestBody @Valid KitchenRq kitchenRq) {
		Kitchen update = kitchenService.update(id, kitchenRq.convert());
		return ResponseEntity.ok(new KitchenRs(update));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override public void deleteById(@PathVariable Long id) { kitchenService.deleteById(id); }

}
