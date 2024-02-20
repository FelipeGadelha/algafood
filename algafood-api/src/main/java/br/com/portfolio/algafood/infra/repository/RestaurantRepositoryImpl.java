package br.com.portfolio.algafood.infra.repository;

import static br.com.portfolio.algafood.infra.specification.RestaurantSpecs.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.portfolio.algafood.domain.model.Restaurant;
import br.com.portfolio.algafood.domain.repository.RestaurantRepository;
import br.com.portfolio.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestaurantRepository restaurantRepository;
	
//	public List<Restaurant> find(String name, BigDecimal taxFreightInit, BigDecimal taxFreightFinal){
//		
////		var jpql = "from Restaurant where name like :name "
////				+ "and taxFreight between :taxFreightInit and :taxFreightFinal";
//		
//		var jpql = new StringBuilder();
//		jpql.append("from Restaurant where 0 = 0 ");
//		var parameters = new HashMap<String, Object>();
//		
//		if (StringUtils.hasLength(name)) { jpql.append("and name like :name "); parameters.put("name", "%"+ name +"%"); }
//		if (taxFreightInit != null) { jpql.append("and taxFreight >= :taxFreightInit "); parameters.put("taxFreightInit", taxFreightInit); }
//		if (taxFreightFinal != null) { jpql.append("and taxFreight <= :taxFreightFinal "); parameters.put("taxFreightFinal", taxFreightFinal); }
//		
//		TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
//		
//		parameters.forEach((k,v) -> query.setParameter(k, v));
//		return query.getResultList();
//	}
	
	@Override
	public List<Restaurant> find(String name, BigDecimal taxInit, BigDecimal taxFinal){
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		var predicates = new ArrayList<Predicate>();
		if (StringUtils.hasLength(name)) predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		if (Objects.nonNull(taxInit)) predicates.add(builder.greaterThanOrEqualTo(root.get("taxFreight"), taxInit));
		if (Objects.nonNull(taxFinal)) predicates.add(builder.lessThanOrEqualTo(root.get("taxFreight"), taxFinal));
		
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Restaurant> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurant> findWithFreeTax(String name) {
		return restaurantRepository.findAll(freeFreight().and(similarName(name)));
	}
	
	
	
}
