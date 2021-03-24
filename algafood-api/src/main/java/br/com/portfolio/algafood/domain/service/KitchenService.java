package br.com.portfolio.algafood.domain.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenService {

	private final KitchenRepository kitchenRepository;
	
	private static final String MSG_KITCHEN_IN_USE = "Cozinha com o ID %d não pode ser removida, pois esta em uso";
	private static final String MSG_KITCHEN_NOT_FOUND = "Não existe Cozinha com o ID %d";

	@Autowired
	public KitchenService(KitchenRepository kitchenRepository) {
		this.kitchenRepository = kitchenRepository;
	}
	
	public List<Kitchen> findAll() {
		return kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		return kitchenRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id)));
	}

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public Kitchen update(Long id, Kitchen updated) {
		Kitchen kitchen = this.findById(id);
		BeanUtils.copyProperties(updated, kitchen, "id");
		return kitchenRepository.save(kitchen);
	}

	public void deleteById(Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id));
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Não existe Cozinha com o ID %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, id));
		}
	}
}
