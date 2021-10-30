package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.portfolio.algafood.core.validation.Groups;
import br.com.portfolio.algafood.core.validation.TaxFreight;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Column(nullable = false)
	private String name;
	
//	@DecimalMin("1")
//	@PositiveOrZero
	@TaxFreight
	@Column(name = "tax_freight", nullable = false)
	private BigDecimal taxFreight;
	
	@JsonIgnore
	@Embedded
	private Address address;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
	@NotNull
	@JsonBackReference
	@ManyToOne(/*fetch = FetchType.LAZY,*/ cascade = { CascadeType.MERGE, CascadeType.ALL })
//	@JsonIgnoreProperties({"hibernateLazyInitializer"})//, "handler"})
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
//	@JsonIgnore
	@ManyToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurant_payment_method", 
		joinColumns = 
			@JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_method_id")
	)
	private List<PaymentMethod> paymentMethod = new ArrayList<>();
	
	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name="creation_date", nullable = false)	
	private OffsetDateTime creationDate;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name="update_date", nullable = false)	
	private OffsetDateTime updateDate;
	
	@Deprecated
	public Restaurant() {	}

	
	
	public Restaurant(Long id, String name, BigDecimal taxFreight, Kitchen kitchen, List<PaymentMethod> paymentMethod,
			Address address, List<Product> products) {
		this.id = id;
		this.name = name;
		this.taxFreight = taxFreight;
		this.kitchen = kitchen;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.products = products;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getTaxFreight() {
		return taxFreight;
	}

	public void setTaxFreight(BigDecimal taxFreight) {
		this.taxFreight = taxFreight;
	}

	public Kitchen getKitchen() {
		return kitchen;
	}

	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	public List<PaymentMethod> getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(List<PaymentMethod> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public OffsetDateTime getUpdateDate() {
		return updateDate;
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
		return "Restaurant [id=" + id + ", name=" + name + ", taxFreight=" + taxFreight + ", kitchen=" + kitchen.getId()
				+ ", creationDate=" + creationDate + ", updateDate=" + updateDate + "]";
	}



}
