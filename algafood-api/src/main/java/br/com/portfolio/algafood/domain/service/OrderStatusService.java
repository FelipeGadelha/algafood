package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.OrderStatus;
import br.com.portfolio.algafood.domain.entity.OrderStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SortedSet;

@Service
public class OrderStatusService {

    private final OrderService orderService;

    @Autowired
    public OrderStatusService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Transactional
    public SortedSet<OrderStatus> findAll(String code) { return orderService.findByCode(code).getOrderStatus(); }

    @Transactional public void confimation(String code) { orderService.findByCode(code).confirm(); }
    @Transactional public void delivered(String code) { orderService.findByCode(code).deliver(); }
    @Transactional public void cancellation(String code) { orderService.findByCode(code).cancel(); }
    @Transactional public OrderStatusType lastStatus(String code) { return orderService.findByCode(code).currentStatus(); }
}
