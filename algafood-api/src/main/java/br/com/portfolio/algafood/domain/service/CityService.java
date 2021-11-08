package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
	
	private final CityRepository cityRepository;
	private final StateService stateService;
	
	private static final String MSG_CITY_IN_USE = "Cidade com o ID %d não pode ser removida, pois esta em uso";
	private static final String MSG_CITY_NOT_FOUND = "Não existe Cidade com o ID %d";

	@Autowired
	public CityService(CityRepository cityRepository, StateService stateService) {
		this.cityRepository = cityRepository;
		this.stateService = stateService;
	}

	@Transactional
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Transactional
	public City findById(Long id) {
		return cityRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id)));
	}

	@Transactional
	public City save(City city) {
		Long stateId = city.getState().getId();
		var state = stateService.findById(stateId);
		city = City.builder()
				.clone(city)
				.state(state)
				.build();
		return cityRepository.save(city);
	}
	@Transactional
	public City update(Long id, City updated) {
		var city = this.findById(id);
		city = City.builder()
				.clone(city)
				.copy(updated)
				.build();
		return this.save(city);
	}

	@Transactional
	public void deleteById(Long id) {
		try {
			cityRepository.deleteById(id);
			cityRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CITY_IN_USE, id));
		}
	}

}
