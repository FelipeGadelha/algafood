package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.CityControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.CityRq;
import br.com.portfolio.algafood.api.v1.dto.response.CityRs;
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

import br.com.portfolio.algafood.domain.model.City;
import br.com.portfolio.algafood.domain.service.CityService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/cities")
public class CityController implements CityControllerOpenApi {
	
	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) { this.cityService = cityService; }


	@JsonView(View.Basic.class)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override public ResponseEntity<List<CityRs>> findAll() {
		return ResponseEntity.ok(cityService.findAll().stream()
				.map(CityRs::new)
				.toList());
	}

	@GetMapping("/{id}")
	@Override public ResponseEntity<CityRs> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new CityRs(cityService.findById(id)));
	}

	@PostMapping
	@Override public ResponseEntity<CityRs> save(@RequestBody @Valid CityRq cityRq) {
		City saved = cityService.save(cityRq.convert());
		return new ResponseEntity<>(new CityRs(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Override public ResponseEntity<CityRs> update(@PathVariable Long id, @RequestBody @Valid CityRq cityRq) {
		City city = cityService.update(id, cityRq.convert());
		return ResponseEntity.ok(new CityRs(city));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override public void deleteById(@PathVariable Long id) { cityService.deleteById(id); }

}
