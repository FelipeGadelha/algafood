package br.com.portfolio.algafood.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.UnaryOperator;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private BigDecimal subtotal;
	private BigDecimal taxFreight;
	private BigDecimal totalValue;
	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod method;
	@ManyToOne @JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	@ManyToOne @JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	@Embedded private Address addressDelivery;
	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private List<OrderItem> ordersItens = new ArrayList<>();
	@ElementCollection
	@CollectionTable(name = "order_status",
			joinColumns = @JoinColumn(name = "order_status_id"))
	@OrderBy("moment asc")
	private SortedSet<OrderStatus> orderStatus = new TreeSet<>();
	@Deprecated public Order() { }

	private Order(Builder builder) {
		this.id = builder.id;
		this.code = (Objects.isNull(builder.code)) ? UUID.randomUUID().toString() : builder.code;
		this.subtotal = builder.ordersItens.stream()
				.map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.taxFreight = (Objects.nonNull(builder.taxFreight)) ? builder.taxFreight : BigDecimal.ZERO;
		this.totalValue = this.subtotal.add(this.taxFreight);
		this.method = builder.method;
		this.restaurant = builder.restaurant;
		this.client = builder.client;
		this.addressDelivery = builder.addressDelivery;
		this.ordersItens = builder.ordersItens;
		this.orderStatus = builder.orderStatus;
	}
//	@PrePersist public void code() { this.code = UUID.randomUUID().toString(); }

	public static Builder builder() { return new Builder(); }

	public static class Builder {
		private Long id;
		private String code;
		private BigDecimal subtotal;
		private BigDecimal taxFreight;
		private BigDecimal totalValue;
		private PaymentMethod method;
		private Restaurant restaurant;
		private User client;
		private Address addressDelivery;
		private List<OrderItem> ordersItens = new ArrayList<>();
		private SortedSet<OrderStatus> orderStatus = new TreeSet<>();
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder code(String code) {
			this.code = code;
			return this;
		}
		public Builder subtotal(BigDecimal subtotal) {
			this.subtotal = subtotal;
			return this;
		}
		public Builder taxFreight(BigDecimal taxFreight) {
			this.taxFreight = taxFreight;
			return this;
		}
		public Builder totalValue(BigDecimal totalValue) {
			this.totalValue = totalValue;
			return this;
		}
		public Builder paymentMethod(PaymentMethod method) {
			this.method = method;
			return this;
		}
		public Builder restaurant(Restaurant restaurant) {
			this.restaurant = restaurant;
			return this;
		}
		public Builder client(User client) {
			this.client = client;
			return this;
		}
		public Builder addressDelivery(Address addressDelivery) {
			this.addressDelivery = addressDelivery;
			return this;
		}
		public Builder addressDelivery(UnaryOperator<Address> func) {
			this.addressDelivery = func.apply(this.addressDelivery);
			return this;
		}
		public Builder ordersItens(List<OrderItem> ordersItens) {
			this.ordersItens = ordersItens;
			return this;
		}
		public Builder orderStatus(SortedSet<OrderStatus> orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}
		public Builder addOrderStatus(OrderStatusType status) {
			this.orderStatus.add(new OrderStatus(status));
			return this;
		}
		public Builder copy(Order order) {
			this.id = (Objects.isNull(id)) ? order.id : this.id;
			this.code = (Objects.isNull(code)) ? order.code : this.code;
			this.subtotal = order.subtotal;
			this.taxFreight = order.taxFreight;
			this.totalValue = order.totalValue;
			this.method = order.method;
			this.restaurant = order.restaurant;
			this.client = order.client;
			this.addressDelivery = order.addressDelivery;
			this.ordersItens = order.ordersItens;
			this.orderStatus = order.orderStatus;
			return this;
		}
		public Builder clone(Order order) {
			this.id = order.id;
			this.code = order.code;
			this.subtotal = order.subtotal;
			this.taxFreight = order.taxFreight;
			this.totalValue = order.totalValue;
			this.method = order.method;
			this.restaurant = order.restaurant;
			this.client = order.client;
			this.addressDelivery = order.addressDelivery;
			this.ordersItens = order.ordersItens;
			this.orderStatus = order.orderStatus;
			return this;
		}
		public Order build() { return new Order(this); }
	}
	public Long getId() { return id; }
	public String getCode() { return code; }
	public BigDecimal getSubtotal() { return subtotal; }
	public BigDecimal getTaxFreight() { return taxFreight; }
	public BigDecimal getTotalValue() { return totalValue; }
	public PaymentMethod getMethod() { return method; }
	public Restaurant getRestaurant() { return restaurant; }
	public User getClient() { return client; }
	public Address getAddressDelivery() { return addressDelivery; }
	public List<OrderItem> getOrdersItens() { return ordersItens; }
	public SortedSet<OrderStatus> getOrderStatus() { return orderStatus; }

	private void calculateTotalValue() {
		this.subtotal = getOrdersItens().stream()
				.map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	public void confirm() {
		Assert.state(this.readyForConfirmation(),
				String.format("Status do pedido %s não pode ser alterado de %s para %s",
						this.getCode(), this.currentStatus().getDescription(), OrderStatusType.CONFIRMED.getDescription()));
		this.orderStatus.add(new OrderStatus(OrderStatusType.CONFIRMED));
	}
	public void deliver() {
		Assert.state(this.readyForDeliver(),
				String.format("Status do pedido %s não pode ser alterado de %s para %s",
						this.getCode(), this.currentStatus().getDescription(), OrderStatusType.DELIVERED.getDescription()));
		this.orderStatus.add(new OrderStatus(OrderStatusType.DELIVERED));
	}
	public void cancel() {
		Assert.state(this.readyForCancellation(),
				String.format("Status do pedido %s não pode ser alterado de %s para %s",
						this.getCode(), this.currentStatus().getDescription(), OrderStatusType.CANCELED.getDescription()));
		this.orderStatus.add(new OrderStatus(OrderStatusType.CANCELED));
	}
	public boolean readyForConfirmation() {
		return !orderStatus.stream()
				.anyMatch(s -> !s.getStatus().equals(OrderStatusType.CREATED));
	}
	public boolean readyForDeliver() {
		return orderStatus.stream()
				.anyMatch(s -> s.getStatus().equals(OrderStatusType.CONFIRMED) &&
						!s.getStatus().equals(OrderStatusType.CANCELED));
	}
	public boolean readyForCancellation() {
		return orderStatus.size() == 1 &&
				orderStatus.stream().anyMatch(s -> s.getStatus().equals(OrderStatusType.CREATED));
	}
	public OrderStatusType currentStatus() { return orderStatus.last().getStatus(); }
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id);
	}
	@Override public int hashCode() { return Objects.hash(id); }

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				"code=" + code +
				", subtotal=" + subtotal +
				", taxFreight=" + taxFreight +
				", totalValue=" + totalValue +
				", method=" + method +
				", addressDelivery=" + addressDelivery +
//				", ordersItens=" + ordersItens +
				", orderStatus=" + orderStatus +
				'}';
	}
}
