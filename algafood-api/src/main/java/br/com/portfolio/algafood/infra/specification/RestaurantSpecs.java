package br.com.portfolio.algafood.infra.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.portfolio.algafood.domain.entity.Restaurant;

public class RestaurantSpecs {
	
	public static Specification<Restaurant> freeFreight(){
		return (root, query, builder) -> builder.equal(root.get("taxFreight"), BigDecimal.ZERO);
	}

	public static Specification<Restaurant> similarName(String name){
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}
	
}
