package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.hateoas.OrderAssembler;
import br.com.portfolio.algafood.api.v1.controller.doc.OrderControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.OrderRq;
import br.com.portfolio.algafood.api.v1.dto.response.OrderDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.config.PageableInterpreter;
import br.com.portfolio.algafood.domain.model.Order;
import br.com.portfolio.algafood.domain.model.User;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
import br.com.portfolio.algafood.domain.service.OrderService;
import ch.qos.logback.core.boolex.EvaluationException;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/orders")
public class OrderController implements OrderControllerOpenApi {

    private final OrderService orderService;
    private final OrderAssembler assembler;
    private final PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @Autowired
    public OrderController(OrderService orderService, OrderAssembler assembler, PagedResourcesAssembler<Order> pagedResourcesAssembler) { this.orderService = orderService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    @Override public PagedModel<OrderRs> search(OrderFilter filter, Pageable pageable) {
        pageable = interpretPageable(pageable);
        var orders = orderService.findAll(filter, pageable);
        return pagedResourcesAssembler.toModel(orders, assembler);
    }
    @GetMapping("/{code}")
    @Override public ResponseEntity<OrderDetailRs> findById(@PathVariable String code) {
        var order = orderService.findByCode(code);
        return ResponseEntity.ok(new OrderDetailRs(order));
    }
    @PostMapping
    @Override public ResponseEntity<OrderDetailRs> save(@RequestBody @Valid OrderRq orderRq) {
        var user = User.builder().id(2L).build();
        var order = orderService.save(orderRq.convert(user));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new OrderDetailRs(order));
    }

    private Pageable interpretPageable(Pageable pageable) {
        var map = Map.of(
                "code", "code",
                "subtotal", "subtotal",
                "taxFreight", "taxFreight",
                "totalValue", "totalValue",
                "restaurant.name", "restaurant.name",
                "restaurant.taxFreight", "restaurant.taxFreight",
                "restaurant.open", "restaurant.open",
                "nameClient", "client.name",
                "client.id", "client.id",
                "client.name", "client.name"
        );
        return PageableInterpreter.interpreter(pageable, map);
    }
}
