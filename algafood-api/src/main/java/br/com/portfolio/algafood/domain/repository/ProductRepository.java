package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.entity.Product;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    @Query("from Product where restaurant.id = :restaurantId and id = :product")
    Optional<Product> findById(@Param("restaurantId") Long restaurantId, @Param("product") Long id);

    List<Product> findByRestaurant(Restaurant restaurant);
}
