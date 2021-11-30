package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.OrderRq;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.domain.entity.User;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
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
    public ResponseEntity<List<OrderRs>> search(OrderFilter filter) {
        return ResponseEntity.ok(orderService.findAll(filter).stream()
                .map(OrderRs::new)
                .collect(Collectors.toList()));
    }
    @GetMapping("/{code}")
    public ResponseEntity<OrderRs> findById(@PathVariable String code) {
        var order = orderService.findByCode(code);
        return ResponseEntity.ok(new OrderRs(order));
    }
    @PostMapping
    public ResponseEntity<OrderRs> save(@RequestBody @Valid OrderRq orderRq) {
        var user = User.builder().id(1L).build();
        var order = orderService.save(orderRq.convert(user));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new OrderRs(order));
    }
}
