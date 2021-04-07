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
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.service.KitchenService;
import br.com.portfolio.algafood.domain.service.Result;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController {

	private final KitchenService kitchenService;

	@Autowired
	public kitchenController(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(kitchenService.findAll());
	}

	@GetMapping("/{kitchenId}")
	public ResponseEntity<?> findById(@PathVariable("kitchenId") Long id) {
		Result<Kitchen> result = kitchenService.findById(id);
		return (result.hasError()) 
				? responseFailDetails(result)
						: ResponseEntity.ok(result.getData());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Kitchen kitchen) {
		return new ResponseEntity<>(kitchenService.save(kitchen), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		Result<Kitchen> result = kitchenService.update(id, kitchen);
		return (result.hasError()) 
				? responseFailDetails(result)
						: ResponseEntity.ok(result.getData());
	}

	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Result<Kitchen> result = kitchenService.deleteById(id);
		return (result.hasError())
				? responseFailDetails(result) 
						: ResponseEntity.noContent().build();
	}
	
	private ResponseEntity<?> responseFailDetails(Result<?> fail) {
		HttpStatus status = (fail.getTitle().equals("Entity Not Found")) 
				? HttpStatus.NOT_FOUND 
						: HttpStatus.BAD_REQUEST;
		return ResponseEntity
					.status(status)
					.body(fail);
	}
}
