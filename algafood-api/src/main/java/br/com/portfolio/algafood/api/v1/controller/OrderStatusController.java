package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.OrderStatusControllerOpenApi;
import br.com.portfolio.algafood.domain.model.OrderStatus;
import br.com.portfolio.algafood.domain.model.OrderStatusType;
import br.com.portfolio.algafood.domain.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.SortedSet;

@RestController
@RequestMapping("/v1/orders/{code}/status")
public class OrderStatusController implements OrderStatusControllerOpenApi {

    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    @Override public ResponseEntity<SortedSet<OrderStatus>> findAll(@PathVariable String code) {
        return ResponseEntity.ok(orderStatusService.findAll(code));
    }
    @GetMapping("/last")
    @Override public ResponseEntity<OrderStatusType> lastStatus(@PathVariable String code) {
        return ResponseEntity.ok(orderStatusService.lastStatus(code));
    }

    @PutMapping("/confimation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void confimation(@PathVariable String code) { orderStatusService.confimation(code); }

    @PutMapping("/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void delivered(@PathVariable String code) { orderStatusService.delivered(code); }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override public void cancellation(@PathVariable String code) { orderStatusService.cancellation(code); }
}
