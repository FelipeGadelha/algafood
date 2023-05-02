package br.com.portfolio.algafood.domain.service;

import java.util.List;

import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.model.State;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.StateRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {
	
	private final StateRepository stateRepository;
	
	private static final String MSG_STATE_IN_USE = "Estado com o ID %d não pode ser removida, pois esta em uso";
	private static final String MSG_STATE_NOT_FOUND = "Não existe Estado com o ID %d";

	@Autowired
	public StateService(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	@Transactional
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	@Transactional
	public State findById(Long id) {
		return stateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id)));
	}

	@Transactional
	public State save(State state) {
		return stateRepository.save(state);
	}

	@Transactional
	public State update(Long id, State updated) {
		State state = this.findById(id);
		state = new State(
				state.getId(),
				updated.getName());
		return stateRepository.save(state);
	}

	@Transactional
	public void deleteById(Long id) {
		try {
			stateRepository.deleteById(id);
			stateRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
		}
		
	}
}
