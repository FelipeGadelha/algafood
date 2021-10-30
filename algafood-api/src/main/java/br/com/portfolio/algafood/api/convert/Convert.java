package br.com.portfolio.algafood.api.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Convert {

	@Autowired
	private ModelMapper mapper;

	public <T, E> E mapper(T source, Class<E> typeDestination) {
		return mapper.map(source, typeDestination);
	}

}
