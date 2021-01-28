package br.com.portfolio.algafood.infra.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.portfolio.algafood.domain.repository.Repository;

@Component
public class RepositoryImpl<T> implements Repository<T>{
	
	@PersistenceContext
	private EntityManager manager;
	private Class<T> clazz;
	
	@Deprecated
	public RepositoryImpl() {	}
	
	public RepositoryImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	
	@Override
	public List<T> findAll(){
		return manager.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
	}
	
	@Override
	public T find(Long id) {
		return manager.find(clazz, id);
	}
	
	@Transactional
	@Override
	public T save(T t) {
		return manager.merge(t);
	}
	
	@Transactional
	@Override
	public void remove(Long id) {
		T t = find(id);
		manager.remove(t);
	}
	

}
