package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.CityControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.CityRq;
import br.com.portfolio.algafood.api.v1.dto.response.CityRs;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
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

import jakarta.validation.Valid;
import org.springframework.hateoas.LinkRelation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/v1/cities")
public class CityController implements CityControllerOpenApi {
	
	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) { this.cityService = cityService; }


//	@JsonView(View.Basic.class)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override public ResponseEntity<CollectionModel<CityRs>> findAll() {
		var list = cityService.findAll().stream()
			.map(CityRs::new)
			.toList();
//			.collect(Collectors.toCollection(TreeSet::new));
		list.forEach(this::addLinks);
		var result = CollectionModel.of(list);
		result.add(
			linkTo(methodOn(this.getClass()).findAll()).withSelfRel()
		);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	@Override public ResponseEntity<CityRs> findById(@PathVariable Long id) {
		var cityRs = new CityRs(cityService.findById(id));
		this.addLinks(cityRs);
		return ResponseEntity.ok(cityRs);
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

	private void addLinks(CityRs cityRs) {
		cityRs.add(
			linkTo(methodOn(this.getClass()).findAll()).withRel(LinkRelation.of("findAll")),
			linkTo(methodOn(this.getClass()).findById(cityRs.getId())).withSelfRel()
		);
		cityRs.getState().add(
			linkTo(methodOn(StateController.class).findAll()).withRel(LinkRelation.of("findAll")),
//			linkTo(methodOn(StateController.class).save(null)).withRel(LinkRelation.of("save")),
//			linkTo(methodOn(StateController.class).update(cityRs.getId(), null)).withRel(LinkRelation.of("update")),
			linkTo(methodOn(StateController.class).findById(cityRs.getId())).withSelfRel()
		);
	}
}
