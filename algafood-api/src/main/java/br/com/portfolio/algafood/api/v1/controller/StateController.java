package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.StateControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.StateRq;
import br.com.portfolio.algafood.api.v1.dto.response.StateRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.LinkRelation;
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

import br.com.portfolio.algafood.domain.model.State;
import br.com.portfolio.algafood.domain.service.StateService;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/states")
public class StateController implements StateControllerOpenApi {
	
	private final StateService stateService;
	
	@Autowired
	public StateController(StateService stateService) {
		this.stateService = stateService;
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}) //, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@Override public ResponseEntity<CollectionModel<StateRs>> findAll() {
		var states = stateService.findAll()
			.stream()
			.map(StateRs::new)
			.toList();
		states.forEach(this::addLinks);
		var result = CollectionModel.of(states);
		result.add(linkTo(methodOn(this.getClass()).findAll()).withSelfRel());

		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	@Override public ResponseEntity<StateRs> findById(@PathVariable Long id) {
		var state = stateService.findById(id);
		var stateRs = new StateRs(state);
		this.addLinks(stateRs);
		return ResponseEntity.ok(stateRs);
	}

	@PostMapping
	@Override public ResponseEntity<StateRs> save(@RequestBody @Valid StateRq stateRq) {
		State saved = stateService.save(stateRq.convert());
		return new ResponseEntity<>(new StateRs(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Override public ResponseEntity<StateRs> update(@PathVariable Long id, @RequestBody @Valid StateRq stateRq) {
		State state = stateService.update(id, stateRq.convert());
		return ResponseEntity.ok(new StateRs(state));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override public void deleteById(@PathVariable Long id) {
		stateService.deleteById(id);
	}

	private void addLinks(StateRs stateRs) {
		stateRs.add(
			linkTo(methodOn(this.getClass()).findAll()).withRel(LinkRelation.of("findAll")),
			linkTo(methodOn(this.getClass()).findById(stateRs.getId())).withSelfRel()
		);
	}
}
