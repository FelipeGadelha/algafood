package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.OrderStatus;
import br.com.portfolio.algafood.domain.model.OrderStatusType;
import br.com.portfolio.algafood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SortedSet;

@Service
public class OrderStatusService {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderStatusService(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }
    @Transactional
    public SortedSet<OrderStatus> findAll(String code) { return orderService.findByCode(code).getOrderStatus(); }

    @Transactional public void confimation(String code) {
        var order = orderService.findByCode(code);
        order.confirm();
        orderRepository.save(order);
    }
    @Transactional public void delivered(String code) {
        orderService.findByCode(code).deliver();
    }
    @Transactional public void cancellation(String code) {
        var order = orderService.findByCode(code);
        order.cancel();
        orderRepository.save(order);
    }
    @Transactional public OrderStatusType lastStatus(String code) {
        return orderService.findByCode(code).currentStatus();
    }
}
