package br.com.portfolio.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.entity.State;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.CityRepository;

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

	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City findById(Long id) {
		return cityRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id)));
	}

	public City save(City city) {
		State state = stateService.findById(city.getState().getId());
		city.setState(state);
		return cityRepository.save(city);
	}

	public City update(Long id, City updated) {
		City city = this.findById(id);
		BeanUtils.copyProperties(updated, city, "id");
		return this.save(city);
	}

	public void deleteById(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CITY_IN_USE, id));
		}
	}

	
	
	
}
