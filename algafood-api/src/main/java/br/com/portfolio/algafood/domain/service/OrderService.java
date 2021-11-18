package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String MSG_ORDER_NOT_FOUND = "Não existe um pedido com código %d";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> findAll() { return orderRepository.findAll(); }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_ORDER_NOT_FOUND, id)));
    }
}
