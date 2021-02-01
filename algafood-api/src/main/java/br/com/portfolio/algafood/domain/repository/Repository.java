package br.com.portfolio.algafood.domain.repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository<T> {

	public List<T> findAll();
	
	public T find(Long id);
	
	public T save(T t);

	void remove(Long id);

}
