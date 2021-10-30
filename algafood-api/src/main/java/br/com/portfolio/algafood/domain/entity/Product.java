package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="product_id_seq")
//	@SequenceGenerator(name="product_id_seq", sequenceName="product_id_seq", allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean active;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	@Deprecated
	public Product() {	}

	public Product(Long id, String name, String description, BigDecimal price, Boolean active, Restaurant restaurant) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.restaurant = restaurant;
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
