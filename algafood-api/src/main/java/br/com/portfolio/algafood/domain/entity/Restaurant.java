package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Restaurant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="restaurant_id_seq", allocationSize=1)
	@Column(name="id")	
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "tax_freight", nullable = false)
	private BigDecimal taxFreight;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@Deprecated
	public Restaurant() {	}

	public Restaurant(Long id, String name, BigDecimal taxFreight, Kitchen kitchen) {
		this.id = id;
		this.name = name;
		this.taxFreight = taxFreight;
		this.kitchen = kitchen;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getTaxFreight() {
		return taxFreight;
	}
	
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
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
		Restaurant other = (Restaurant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", taxFreight=" + taxFreight + ", kitchen=" + kitchen + "]";
	}

	
	
}
