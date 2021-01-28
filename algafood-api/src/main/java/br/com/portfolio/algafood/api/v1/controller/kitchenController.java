package br.com.portfolio.algafood.api.v1.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import br.com.portfolio.algafood.api.v1.model.xml.KitchensWrapper;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.infra.repository.KitchenRepositoryImpl;

@RestController
@RequestMapping(value = "/v1/kitchens") //produces = MediaType.APPLICATION_JSON_VALUE)
public class kitchenController {
	
	@Autowired
	private KitchenRepositoryImpl repository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Kitchen> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public KitchensWrapper findAllXml() {
		return new KitchensWrapper(repository.findAll());
	}
	
	@GetMapping("/{kitchenId}")
	public ResponseEntity<?> findById(@PathVariable("kitchenId") Long id) {
		Kitchen kitchen = repository.find(id);
		if (Objects.nonNull(kitchen)) {
			return ResponseEntity.ok(kitchen);
		};
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return repository.save(kitchen);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
		Kitchen entity = repository.find(id);
		if (Objects.nonNull(entity)) {
	//		updated.setName(kitchen.getName());		
			BeanUtils.copyProperties(kitchen, entity, "id");
			Kitchen save = repository.save(entity);
			return ResponseEntity.ok(save);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			Kitchen entity = repository.find(id);
			if (Objects.nonNull(entity)) {
				repository.remove(entity.getId());
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	

}
