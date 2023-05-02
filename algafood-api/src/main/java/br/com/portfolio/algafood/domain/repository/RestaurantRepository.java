package br.com.portfolio.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, 
		RestaurantRepositoryQueries, 
		JpaSpecificationExecutor<Restaurant> {
	
	@Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.kitchen LEFT JOIN FETCH r.address.city c LEFT JOIN FETCH c.state LEFT JOIN FETCH r.paymentMethod")
	List<Restaurant> findAll();
	
	List<Restaurant> findByTaxFreightBetween(BigDecimal taxInit, BigDecimal taxFinal);
	
//	@Query("from Restaurant where name like %:name% and kitchen.id = :id")
	List<Restaurant> findByName(String name, @Param("id") Long kitchenId);
	
//	List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
	
	Optional<Restaurant> findFirstRestaurantByNameContaining(String name);
	
	List<Restaurant> findTop2ByNameContaining(String name);
	
	boolean existsByName(String name);
	
	int countByKitchenId(Long kitchen);
	
}
