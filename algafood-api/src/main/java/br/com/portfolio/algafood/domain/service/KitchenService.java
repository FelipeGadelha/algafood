package br.com.portfolio.algafood.domain.service;


import java.util.List;
import java.util.Optional;

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

	@Autowired
	public KitchenService(KitchenRepository kitchenRepository) {
		this.kitchenRepository = kitchenRepository;
	}
	
	public List<Kitchen> findAll() {
		return kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		Optional<Kitchen> entity = kitchenRepository.findById(id);
		if (entity.isEmpty()) throw new EntityNotFoundException(String.format("Não existe Cozinha com o ID %d", id));
		return entity.get();
	}

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public Kitchen update(Kitchen kitchen) {
		Optional<Kitchen> optional = kitchenRepository.findById(kitchen.getId());
		if (optional.isEmpty()) throw new EntityNotFoundException(String.format("Não existe Cozinha com o ID %d", kitchen.getId()));
		return kitchenRepository.save(kitchen);
	}

	public void deleteById(Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Não existe Cozinha com o ID %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Cozinha com o ID %d não pode ser removida, pois esta em uso", id));
		}
	}
}
