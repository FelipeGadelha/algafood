package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.OrderRq;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.entity.User;
import br.com.portfolio.algafood.domain.service.OrderService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @JsonView(View.Basic.class)
    public ResponseEntity<List<OrderRs>> findAll() {
        return ResponseEntity.ok(orderService.findAll().stream()
                .map(OrderRs::new)
                .collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @JsonView(View.Detail.class)
    public ResponseEntity<OrderRs> findById(@PathVariable Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(new OrderRs(order));
    }
    @PostMapping
    @JsonView(View.Detail.class)
    public ResponseEntity<OrderRs> save(@RequestBody @Valid OrderRq orderRq) {
        var user = User.builder().id(1L).build();
        var order = orderService.save(orderRq.convert(user));
        System.err.println(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new OrderRs(order));
    }
}
