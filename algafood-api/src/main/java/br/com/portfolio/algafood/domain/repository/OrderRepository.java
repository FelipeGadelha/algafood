package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Optional<Order> findByCode(String code);

    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "method",
                    "restaurant.kitchen",
                    "client",
                    "ordersItens.order",
                    "ordersItens.product",
                    "orderStatus"
            }
    )
    Page<Order> findAll(Specification<Order> specification, Pageable pageable);

    @Query("from Order o join fetch o.client join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAll();
}
