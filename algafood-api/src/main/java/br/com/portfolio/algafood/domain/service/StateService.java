package br.com.portfolio.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.StateRepository;

@Service
public class StateService {
	
	private final StateRepository stateRepository;
	
	private static final String MSG_STATE_IN_USE = "Estado com o ID %d não pode ser removida, pois esta em uso";
	private static final String MSG_STATE_NOT_FOUND = "Não existe Estado com o ID %d";

	@Autowired
	public StateService(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	public List<State> findAll() {
		return stateRepository.findAll();
	}

	public State findById(Long id) {
		return stateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id)));
	}

	public State save(State state) {
		return stateRepository.save(state);
	}

	public State update(Long id, State updated) {
		State state = this.findById(id);
		System.err.println(state);
		BeanUtils.copyProperties(updated, state, "id");
		return stateRepository.save(state);
	}

	public void deleteById(Long id) {
		try {
			stateRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id));
		} 
//		catch (DataIntegrityViolationException e) {
//			throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
//		}
		
	}

}
