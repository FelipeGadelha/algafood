package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.StateRq;
import br.com.portfolio.algafood.api.v1.dto.response.StateRs;
import com.fasterxml.jackson.annotation.JsonView;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity<List<StateRs>> findAll() {
		return ResponseEntity.ok(stateService.findAll()
				.stream()
				.map(StateRs::new)
				.collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateRs> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new StateRs(stateService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<StateRs> save(@RequestBody @Valid StateRq stateRq) {
		State saved = stateService.save(stateRq.convert());
		return new ResponseEntity<>(new StateRs(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StateRs> update(@PathVariable Long id, @RequestBody @Valid StateRq stateRq) {
		State state = stateService.update(id, stateRq.convert());
		return ResponseEntity.ok(new StateRs(state));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		stateService.deleteById(id);
	}
}
