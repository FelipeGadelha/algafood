package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.kitchenControllerOpenApi;
import br.com.portfolio.algafood.api.hateoas.KitchenAssembler;
import br.com.portfolio.algafood.api.v1.dto.request.KitchenRq;
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/kitchens") // produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController implements kitchenControllerOpenApi {

	private final KitchenService kitchenService;
	private final KitchenAssembler assembler;
	private final PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

	@Autowired
	public kitchenController(KitchenService kitchenService, KitchenAssembler assembler, PagedResourcesAssembler<Kitchen> pagedResourcesAssembler) {
		this.kitchenService = kitchenService;
		this.assembler = assembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@GetMapping
	@Override public CollectionModel<KitchenRs> findAll(@PageableDefault(size = 10) Pageable pageable) {
		var kitchens = kitchenService.findAll(pageable);
		return pagedResourcesAssembler.toModel(kitchens, assembler);
//			.stream().collect(Collectors.toCollection(
//				() -> new TreeSet<>(Comparator.comparing(KitchenRs::getName)))
//			);
//		kitchenRs.forEach(this::addLinks);
//		var result = PagedModel.of(kitchenRs);

//		return result;
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

	private void addLinks(KitchenRs kitchenRs) {
		kitchenRs.add(
//			linkTo(methodOn(this.getClass()).findAll()).withRel(LinkRelation.of("findAll")),
			linkTo(methodOn(this.getClass()).findById(kitchenRs.getId())).withSelfRel()
		);
//        userRs.getState().add(
//            linkTo(methodOn(StateController.class).findAll()).withRel(LinkRelation.of("findAll")),
//            linkTo(methodOn(StateController.class).findById(userRs.getId())).withSelfRel()
//        );
	}
}
