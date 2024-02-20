package br.com.portfolio.algafood.api.hateoas;

import br.com.portfolio.algafood.api.v1.controller.OrderController;
import br.com.portfolio.algafood.api.v1.controller.RestaurantController;
import br.com.portfolio.algafood.api.v1.controller.StateController;
import br.com.portfolio.algafood.api.v1.controller.UserController;
import br.com.portfolio.algafood.api.v1.dto.response.CityRs;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.domain.model.Order;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<Order, OrderRs> {

    public OrderAssembler() {
        super(OrderController.class, OrderRs.class);
    }

    @Override
    public OrderRs toModel(Order entity) {
        var order = new OrderRs(entity)
            .add(
                linkTo(methodOn(OrderController.class).search(null, null)).withRel(LinkRelation.of("search")),
                linkTo(methodOn(OrderController.class).findById(entity.getCode())).withSelfRel()
            );
        order.getClient().add(
            linkTo(methodOn(UserController.class).findAll()).withRel(LinkRelation.of("findAll")),
            linkTo(methodOn(UserController.class).findById(entity.getClient().getId())).withSelfRel()
        );
        order.getRestaurant().add(
            linkTo(methodOn(RestaurantController.class).findAll()).withRel(LinkRelation.of("findAll")),
            linkTo(methodOn(RestaurantController.class).findById(entity.getRestaurant().getId())).withSelfRel()
        );
        return order;
    }
}
