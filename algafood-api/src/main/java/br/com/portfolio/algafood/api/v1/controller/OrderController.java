package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.OrderRq;
import br.com.portfolio.algafood.api.v1.dto.response.OrderDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.config.PageableInterpreter;
import br.com.portfolio.algafood.domain.model.User;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
import br.com.portfolio.algafood.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
//    @JsonView(View.Basic.class)
    public ResponseEntity<Page<OrderRs>> search(OrderFilter filter, Pageable pageable) {
        pageable = interpretPageable(pageable);
        return ResponseEntity.ok(orderService.findAll(filter, pageable).map(OrderRs::new));
    }
    @GetMapping("/{code}")
    public ResponseEntity<OrderDetailRs> findById(@PathVariable String code) {
        var order = orderService.findByCode(code);
        return ResponseEntity.ok(new OrderDetailRs(order));
    }
    @PostMapping
    public ResponseEntity<OrderDetailRs> save(@RequestBody @Valid OrderRq orderRq) {
        var user = User.builder().id(1L).build();
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
