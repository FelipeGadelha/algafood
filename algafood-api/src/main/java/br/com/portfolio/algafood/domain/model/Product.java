package br.com.portfolio.algafood.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean active;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	@Deprecated public Product() { }

	private Product(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.active = builder.active;
		this.restaurant = builder.restaurant;
	}
	public static Builder builder() { return new Builder(); }
	public static class Builder {
		private Long id;
		private String name;
		private String description;
		private BigDecimal price;
		private Boolean active;
		private Restaurant restaurant;
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}
		public Builder restaurant(Restaurant restaurant) {
			this.restaurant = restaurant;
			return this;
		}
		public Builder copy(Product product) {
			this.id = (Objects.isNull(id)) ? product.id : this.id;
			this.name = product.name;
			this.description = product.description;
			this.price = product.price;
			this.active = product.active;
			this.restaurant = (Objects.isNull(restaurant)) ? product.restaurant : this.restaurant;
			return this;
		}
		public Builder clone(Product product) {
			this.id = product.id;
			this.name = product.name;
			this.description = product.description;
			this.price = product.price;
			this.active = product.active;
			this.restaurant = product.restaurant;
			return this;
		}
		public Product build() { return new Product(this); }
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Boolean getActive() {
		return active;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", active=" + active + ", restaurant=" + restaurant + "]";
	}
}
