package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {

	private final KitchenRepository kitchenRepository;
	
	private static final String MSG_KITCHEN_IN_USE = "Cozinha com o ID %d não pode ser removida, pois esta em uso";
	private static final String MSG_KITCHEN_NOT_FOUND = "Não existe Cozinha com o ID %d";

	@Autowired
	public KitchenService(KitchenRepository kitchenRepository) {
		this.kitchenRepository = kitchenRepository;
	}
	
	public Page<Kitchen> findAll(Pageable pageable) {
		return kitchenRepository.findAll(pageable);
	}

	@Transactional
	public Kitchen findById(Long id) {
		return kitchenRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id)));
	}

	@Transactional
	public Kitchen save(Kitchen kitchen) { return kitchenRepository.save(kitchen); }

	@Transactional
	public Kitchen update(Long id, Kitchen updated) {
		var kitchen = this.findById(id);
		kitchen = new Kitchen(
				kitchen.getId(),
				updated.getName());
		return kitchenRepository.save(kitchen);
	}

	@Transactional
	public void deleteById(Long id) {
		try {
			kitchenRepository.deleteById(id);
			kitchenRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, id));
		}
	}
}
