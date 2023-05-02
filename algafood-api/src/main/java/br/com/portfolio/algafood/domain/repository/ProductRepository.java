package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.model.ProductImage;
import br.com.portfolio.algafood.domain.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurantId and id = :product")
    Optional<Product> findById(@Param("restaurantId") Long restaurantId, @Param("product") Long id);

    @Query("from Product p where p.active = true and p.restaurant.id = :restaurantId")
    List<Product> findByActiveRestaurants(Long restaurantId);

    List<Product> findByRestaurantId(Long restaurantId);

    @Query("select pi from ProductImage pi join pi.product p " +
            "where p.restaurant.id = :restaurantId and pi.product.id = :productId")
    Optional<ProductImage> findPhotoById(Long restaurantId, Long productId);
}
