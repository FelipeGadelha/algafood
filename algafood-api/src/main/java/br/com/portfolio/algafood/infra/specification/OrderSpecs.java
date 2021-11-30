package br.com.portfolio.algafood.infra.specification;

import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    public static Specification<Order> useFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (filter.getClientId() != null) predicates.add((builder.equal(root.get("client"), filter.getClientId())));
            if (filter.getRestaurantId() != null) predicates.add((builder.equal(root.get("restaurant"), filter.getRestaurantId())));
            if (filter.getStatus() != null) predicates
                    .add((builder.equal(root.join("orderStatus", JoinType.LEFT)
                            .get("status"), filter.getStatus())));

            if (filter.getCreationDate() != null) predicates
                    .add((builder.greaterThanOrEqualTo(root
                            .join("orderStatus", JoinType.LEFT)     //.<Order>get("orderStatus")
                            .get("moment"), filter.getCreationDate())));

            if (filter.getFinishDate() != null) predicates
                    .add((builder.lessThanOrEqualTo(root
                            .join("orderStatus", JoinType.LEFT)
                            .get("moment"), filter.getFinishDate())));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
//            https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/outer-left-join.html
//            https://stackoverflow.com/questions/29023182/using-an-embeddable-entity-in-a-jpa-criteriaquery
//            https://www.google.com/search?q=how+to+specification+entity+for+embeddable+objects+in+hibernate&rlz=1C1GCEA_enBR971BR971&biw=1920&bih=937&sxsrf=AOaemvKUIRdUylGKxf66WIStTgC_RMZEHQ%3A1638243429436&ei=ZZylYY_IGfbT1sQPgey14As&ved=0ahUKEwjPjqqtlL_0AhX2qZUCHQF2DbwQ4dUDCA8&uact=5&oq=how+to+specification+entity+for+embeddable+objects+in+hibernate&gs_lcp=Cgdnd3Mtd2l6EAM6BwgjELADECc6BwgAEEcQsAM6BQgAEM0CSgQIQRgAUJJMWIZtYM-NAWgHcAJ4AIABpAGIAZAQkgEEMC4xNpgBAKABAcgBCcABAQ&sclient=gws-wiz
//            https://github.com/ZhongjunTian/spring-repository-plus/issues/6

//select * from orders o
//        left join order_status os on o.id = os.order_status_id
//        where
//        o.user_client_id = 1 and
//        restaurant_id = 1 and
//        os.status = 'CREATED' and
//        os.moment >= '2021-10-10T10:00:00Z' and
//        os.moment <= '2021-12-20T10:00:00Z';
