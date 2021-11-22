package br.com.portfolio.algafood.domain.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.UnaryOperator;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxFreight;
	private BigDecimal totalValue;
	@CreationTimestamp @Column(name="creation_date", nullable = false)
	private OffsetDateTime creationDate;
	private OffsetDateTime confirmationDate;
	private OffsetDateTime cancelDate;
	private OffsetDateTime deliveryDate;
	@Enumerated(EnumType.STRING)
	private OrderStatusType status = OrderStatusType.CREATED;
	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod method;
	@ManyToOne @JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	@ManyToOne @JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	@Embedded private Address addressDelivery;
	@OneToMany(mappedBy = "order")
	private List<OrderItem> ordersItens = new ArrayList<>();
	@ElementCollection
	@CollectionTable(name = "order_status",
			joinColumns = @JoinColumn(name = "order_status_id"))
	private Set<OrderStatus> orderStatus = new HashSet<>();
	@Deprecated public Order() { }

	private Order(Builder builder) {
		this.id = builder.id;
		this.subtotal = builder.ordersItens.stream()
				.map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.taxFreight = (Objects.nonNull(builder.taxFreight)) ? builder.taxFreight : BigDecimal.ZERO;
		this.totalValue = this.subtotal.add(this.taxFreight);
		this.creationDate = builder.creationDate;
		this.confirmationDate = builder.confirmationDate;
		this.cancelDate = builder.cancelDate;
		this.deliveryDate = builder.deliveryDate;
		this.status = (Objects.nonNull(builder.status)) ? builder.status : OrderStatusType.CREATED;
		this.method = builder.method;
		this.restaurant = builder.restaurant;
		this.client = builder.client;
		this.addressDelivery = builder.addressDelivery;
		this.ordersItens = builder.ordersItens;
		this.orderStatus = builder.orderStatus;
	}

	public static Builder builder() { return new Builder(); }
	public static class Builder {
		private Long id;
		private BigDecimal subtotal;
		private BigDecimal taxFreight;
		private BigDecimal totalValue;
		private OffsetDateTime creationDate;
		private OffsetDateTime confirmationDate;
		private OffsetDateTime cancelDate;
		private OffsetDateTime deliveryDate;
		private OrderStatusType status;
		private PaymentMethod method;
		private Restaurant restaurant;
		private User client;
		private Address addressDelivery;
		private List<OrderItem> ordersItens = new ArrayList<>();
		private Set<OrderStatus> orderStatus = new HashSet<>();
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
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
		public Builder creationDate(OffsetDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		public Builder confirmationDate(OffsetDateTime confirmationDate) {
			this.confirmationDate = confirmationDate;
			return this;
		}
		public Builder cancelDate(OffsetDateTime cancelDate) {
			this.cancelDate = cancelDate;
			return this;
		}
		public Builder deliveryDate(OffsetDateTime deliveryDate) {
			this.deliveryDate = deliveryDate;
			return this;
		}
		public Builder status(OrderStatusType status) {
			this.status = status;
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
		public Builder orderStatus(Set<OrderStatus> orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}
		public Builder copy(Order order) {
			this.id = (Objects.isNull(id)) ? order.id : this.id;
			this.subtotal = order.subtotal;
			this.taxFreight = order.taxFreight;
			this.totalValue = order.totalValue;
			this.creationDate = order.creationDate;
			this.confirmationDate = order.confirmationDate;
			this.cancelDate = order.cancelDate;
			this.deliveryDate = order.deliveryDate;
			this.status = order.status;
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
			this.subtotal = order.subtotal;
			this.taxFreight = order.taxFreight;
			this.totalValue = order.totalValue;
			this.creationDate = order.creationDate;
			this.confirmationDate = order.confirmationDate;
			this.cancelDate = order.cancelDate;
			this.deliveryDate = order.deliveryDate;
			this.status = order.status;
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
	public BigDecimal getSubtotal() { return subtotal; }
	public BigDecimal getTaxFreight() { return taxFreight; }
	public BigDecimal getTotalValue() { return totalValue; }
	public OffsetDateTime getCreationDate() { return creationDate; }
	public OffsetDateTime getConfirmationDate() { return confirmationDate; }
	public OffsetDateTime getCancelDate() { return cancelDate; }
	public OffsetDateTime getDeliveryDate() { return deliveryDate; }
	public OrderStatusType getStatus() { return status; }
	public PaymentMethod getMethod() { return method; }
	public Restaurant getRestaurant() { return restaurant; }
	public User getClient() { return client; }
	public Address getAddressDelivery() { return addressDelivery; }
	public List<OrderItem> getOrdersItens() { return ordersItens; }
	public Set<OrderStatus> getOrderStatus() { return orderStatus; }

	private void calculateTotalValue() {
		this.subtotal = getOrdersItens().stream()
				.map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

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
				", subtotal=" + subtotal +
				", taxFreight=" + taxFreight +
				", totalValue=" + totalValue +
				", creationDate=" + creationDate +
				", confirmationDate=" + confirmationDate +
				", cancelDate=" + cancelDate +
				", deliveryDate=" + deliveryDate +
				", status=" + status +
				", method=" + method +
				", addressDelivery=" + addressDelivery +
				", ordersItens=" + ordersItens +
				", orderStatus=" + orderStatus +
				'}';
	}
}
