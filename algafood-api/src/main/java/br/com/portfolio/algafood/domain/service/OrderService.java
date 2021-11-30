package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.*;
import br.com.portfolio.algafood.domain.exception.BusinessException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
import br.com.portfolio.algafood.domain.repository.OrderRepository;
import br.com.portfolio.algafood.infra.specification.OrderSpecs;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final PaymentMethodService paymentMethodService;
    private final ProductService productService;
    private final UserService userService;
    private final CityService cityService;
    private static final String MSG_ORDER_NOT_FOUND = "Não existe um pedido com código %s";

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        RestaurantService restaurantService,
                        PaymentMethodService paymentMethodService,
                        ProductService productService,
                        UserService userService,
                        CityService cityService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.paymentMethodService = paymentMethodService;
        this.productService = productService;
        this.userService = userService;
        this.cityService = cityService;
    }

    @Transactional
    public List<Order> findAll(OrderFilter filter) { return orderRepository.findAll(OrderSpecs.useFilter(filter)); }

    @Transactional
    public Order findByCode(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_ORDER_NOT_FOUND, code)));
    }

    @Transactional
    public Order save(final Order order) {
        Long restaurantId = order.getRestaurant().getId();
        Long methodId = order.getMethod().getId();
        Long cityId = order.getAddressDelivery().getCity().getId();
        Long clientId = order.getClient().getId();
        var restaurant = restaurantService.findById(restaurantId);
        var method = paymentMethodService.findById(methodId);
        var city = cityService.findById(cityId);
        var client = userService.findById(clientId);

        var itens = order.getOrdersItens().stream()
                .map(item -> {
                    var product = productService.findById(restaurantId, item.getProduct().getId());
                    return OrderItem.builder()
                            .clone(item)
                            .product(product)
                            .order(order)
                            .unitPrice(product.getPrice())
                            .build();
                }).collect(Collectors.toList());

        if (!restaurant.acceptPaymentMethod(method))
            throw new BusinessException(String
                    .format("Forma de pagamento '%s' não é aceito por esse restaurante.", method.getDescription()));

        var result = Order.builder()
                .clone(order)
                .restaurant(restaurant)
                .client(client)
                .addressDelivery(a -> Address.builder()
                        .clone(a)
                        .city(city)
                        .build()
                ).paymentMethod(method)
                .taxFreight(order.getRestaurant().getTaxFreight())
                .ordersItens(itens)
                .addOrderStatus(OrderStatusType.CREATED)
                .build();
        return orderRepository.save(result);
    }

    private List<OrderItem> validateItems(Order order, Long restaurantId) {
        return order.getOrdersItens().stream()
                .map(item -> {
                    var product = productService.findById(restaurantId, item.getProduct().getId());
                    return OrderItem.builder()
                            .clone(item)
                            .product(product)
                            .order(order)
                            .unitPrice(product.getPrice())
                            .build();
                }).collect(Collectors.toList());
    }
}
