package br.com.portfolio.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.Kitchen;
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
	
	public Result<?> findAll() {
		return new Result<>(kitchenRepository.findAll());
	}

	public Result<Kitchen> findById(Long id) {
		Optional<Kitchen> optional = kitchenRepository.findById(id);
		return (optional.isPresent()) 
				? new Result<>(optional.get()) 
						: this.notFound(id);
		
//		return kitchenRepository.findById(id)
//			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id)));
	}

	public Result<Kitchen> save(Kitchen kitchen) {
		return new Result<>(kitchenRepository.save(kitchen));
	}

	public Result<Kitchen> update(Long id, Kitchen updated) {
		Result<Kitchen> result = this.findById(id);
		if(result.hasError()) return result;
		Kitchen kitchen = result.getData();
		BeanUtils.copyProperties(updated, kitchen, "id");
		return new Result<>(kitchenRepository.save(kitchen));
	}

	public Result<Kitchen> deleteById(Long id) {
		try {
			kitchenRepository.deleteById(id);
			return Result.empty();
		} catch (EmptyResultDataAccessException e) {
			return this.notFound(id);
//			throw new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id));
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Não existe Cozinha com o ID %d", id));
		} catch (DataIntegrityViolationException e) {
			return this.inUse(id);
//			throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, id));
		}
	}
	private Result<Kitchen> notFound(Long id) {
		return new Result.Builder<Kitchen>()
					.title("Entity Not Found")
					.details(String.format(MSG_KITCHEN_NOT_FOUND, id))
					.build();
				
	}
	
	private Result<Kitchen> inUse(Long id) {
		return new Result.Builder<Kitchen>()
					.title("Entity In Use")
					.details(String.format(MSG_KITCHEN_IN_USE, id))
					.build();
	}
	
}
