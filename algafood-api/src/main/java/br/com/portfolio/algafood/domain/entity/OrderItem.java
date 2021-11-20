package br.com.portfolio.algafood.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private String observation;
	@ManyToOne @JoinColumn(name = "order_id", nullable = false)
	private Order order;
	@ManyToOne @JoinColumn(nullable = false)
	private Product product;

	@Deprecated public OrderItem() { }
	
	private OrderItem(Builder builder) {
		this.id = builder.id;
		this.quantity = builder.quantity;
		this.unitPrice = builder.unitPrice;
		if (builder.unitPrice == null) { this.unitPrice = BigDecimal.ZERO; }
		if (builder.quantity == null) { this.quantity = 0; }
		this.totalPrice = calculateTotalValue(builder.quantity);
		this.observation = builder.observation;
		this.order = builder.order;
		this.product = builder.product;
	}
	public static Builder builder() { return new Builder(); }

	public static class Builder {
		private Long id;
		private Integer quantity;
		private BigDecimal unitPrice;
		private BigDecimal totalPrice;
		private String observation;
		private Order order;
		private Product product;
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		public Builder unitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
			return this;
		}
		public Builder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}
		public Builder observation(String observation) {
			this.observation = observation;
			return this;
		}
		public Builder order(Order order) {
			this.order = order;
			return this;
		}
		public Builder product(Product product) {
			this.product = product;
			return this;
		}
		public Builder clone(OrderItem item) {
			this.id = item.id;
			this.quantity = item.quantity;
			this.unitPrice = item.unitPrice;
			this.totalPrice = item.totalPrice;
			this.observation = item.observation;
			this.order = item.order;
			this.product = item.product;
			return this;
		}
		public Builder copy(OrderItem item) {
			this.id = (Objects.isNull(id)) ? item.id : this.id;
			this.quantity = item.quantity;
			this.unitPrice = item.unitPrice;
			this.totalPrice = item.totalPrice;
			this.observation = item.observation;
			this.order = item.order;
			this.product = item.product;
			return this;
		}
		public OrderItem build() { return new OrderItem(this); }
	}
	public Long getId() {
		return id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public BigDecimal getUnitPrice() { return unitPrice; }
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public String getObservation() {
		return observation;
	}
	public Product getProduct() {
		return product;
	}
	public Order getOrder() { return order; }
	private BigDecimal calculateTotalValue(Integer quantity) { return this.unitPrice.multiply(new BigDecimal(quantity)); }

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", quantity=" + quantity +
				", unitPrice=" + unitPrice +
				", totalPrice=" + totalPrice +
				", observation='" + observation + '\'' +
				", order=" + order +
				", product=" + product +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderItem orderItem = (OrderItem) o;
		return Objects.equals(id, orderItem.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
