package br.com.portfolio.algafood.infra.search;

import br.com.portfolio.algafood.domain.entity.DailySale;
import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.entity.OrderStatus;
import br.com.portfolio.algafood.domain.entity.OrderStatusType;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.search.SalesSearch;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SalesSearchImpl implements SalesSearch {

    @PersistenceContext private EntityManager manager;

    @Override
    public List<DailySale> findDailySale(DailySaleFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);
        var orderStatus = root.join("orderStatus", JoinType.LEFT);

        var predicates = new ArrayList<Predicate>();

        var functionConvertTzMoment = builder.function(
                "timezone",
                Date.class,
                builder.literal("+00:00"),
                orderStatus.get("moment")
        );

        var functionDate = builder.function(
                "TO_CHAR",
                String.class,
                functionConvertTzMoment,
                builder.literal("yyyy-MM-dd")
        );

        var selection = builder.construct(DailySale.class,
                functionDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        if (filter.getRestaurantId() != null)
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));

        if (filter.getCreationDate() != null) predicates
                .add((builder.greaterThanOrEqualTo(orderStatus.get("moment"), filter.getCreationDate())));

        if (filter.getFinishDate() != null) predicates
                .add((builder.lessThanOrEqualTo(orderStatus.get("moment"), filter.getFinishDate())));

        predicates.add(orderStatus.get("status").in(OrderStatusType.DELIVERED));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDate);
        return manager.createQuery(query).getResultList();
    }



    private List<DailySale> findByJPQL(DailySaleFilter filter) {
        String jpql = "SELECT date(os.moment) as date, count(o.id) as totalSales, sum(o.total_value) as totalIncome " +
                "FROM Order o left join order_status os on o.id = os.order_status_id WHERE 1=1 ";

        if (filter.getRestaurantId() != null) {
            jpql = " AND o.restaurantId = :restaurantId ";
        }
        if (filter != null) {
            jpql = " AND p.dataCadastro = :dataCadastro ";
        }
        Projections.avg("");
        var query = manager.createQuery(jpql, DailySale.class);

        return query.getResultList();
    }
}

//        if (filter.getRestaurantId() != null)
//            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
//
//        if (filter.getCreationDate() != null) predicates
//                .add((builder.greaterThanOrEqualTo(root
//                        .join("orderStatus", JoinType.LEFT)
//                        .get("moment"), filter.getCreationDate())));
//
//        if (filter.getFinishDate() != null) predicates
//                .add((builder.lessThanOrEqualTo(root
//                        .join("orderStatus", JoinType.LEFT)
//                        .get("moment"), filter.getFinishDate())));
//
//        predicates.add(root.join("orderStatus", JoinType.LEFT)
//                .get("status").in(OrderStatusType.DELIVERED));

//            select
//            date(os.moment) as date,
//            count(o.id) as totalSales,
//            sum(o.total_value) as totalIncome
//            from orders o
//            left join order_status os on o.id = os.order_status_id
//            where os.status = 'DELIVERED'
//            group by date(os.moment);

//https://thorben-janssen.com/builder-pattern-hibernate/
//    Order o = new Order.OrderBuilder()
//            .withOrderDate(LocalDate.now())
//            .withItems()
//                  .addItem().withProduct(p1).withQuantity(1).addToList()
//                  .addItem().withProduct(p2).withQuantity(2).addToList()
//            .buildItemList()
//            .buildOrder();