package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_id_seq")
//	@SequenceGenerator(name="order_id_seq", sequenceName="order_id_seq", allocationSize = 1)
	@Column(name="id")	
	private Long id;
	
	private BigDecimal subtotal;
	private BigDecimal taxFreight;
	private BigDecimal totalValue;
	@JsonIgnore
	@CreationTimestamp
	@Column(name="creation_date", nullable = false)	
	private OffsetDateTime creationDate;
	private OffsetDateTime confirmationDate;
	private OffsetDateTime cancelDate;
	private OffsetDateTime deliveryDate;
	
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod method;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	
	@JsonIgnore
	@Embedded
	private Address addressDelivery;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> ordersItens = new ArrayList<>();
	
	public Order() { }
	
	public Order(Long id, BigDecimal subtotal, BigDecimal taxFreight, PaymentMethod method, Restaurant restaurant, User client,
			Address addressDelivery, List<OrderItem> ordersItens) {
		this.id = id;
		this.subtotal = subtotal;
		this.taxFreight = taxFreight;
		this.method = method;
		this.restaurant = restaurant;
		this.client = client;
		this.addressDelivery = addressDelivery;
		this.ordersItens = ordersItens;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTaxFreight() {
		return taxFreight;
	}

	public void setTaxFreight(BigDecimal taxFreight) {
		this.taxFreight = taxFreight;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public OffsetDateTime getConfirmationDate() {
		return confirmationDate;
	}

	public OffsetDateTime getCancelDate() {
		return cancelDate;
	}

	public OffsetDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Address getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(Address addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public List<OrderItem> getOrdersItens() {
		return ordersItens;
	}

	public void setOrdersItens(List<OrderItem> ordersItens) {
		this.ordersItens = ordersItens;
	}
	
	
	
}
