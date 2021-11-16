package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty @NotBlank
	@Column(nullable = false)
	private String name;

//	@DecimalMin("1")
//	@PositiveOrZero
	@TaxFreight @Column(name = "tax_freight", nullable = false)
	private BigDecimal taxFreight;
	
	@Embedded
	private Address address;

//	@JsonIgnoreProperties(value = "name", allowGetters = true)
	@NotNull @ManyToOne(/*fetch = FetchType.LAZY,*/ cascade = { CascadeType.MERGE, CascadeType.ALL })
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;

	@ManyToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurant_payment_method", 
		joinColumns = 
			@JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "payment_method_id")
	)
	private List<PaymentMethod> paymentMethod = new ArrayList<>();

//	@JsonManagedReference
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();

	private Boolean active = Boolean.TRUE;
	
	@CreationTimestamp
	@Column(name="creation_date", nullable = false)	
	private OffsetDateTime creationDate;

	@UpdateTimestamp
	@Column(name="update_date", nullable = false)	
	private OffsetDateTime updateDate;
	
	@Deprecated public Restaurant() {	}

	public Restaurant(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.taxFreight = builder.taxFreight;
		this.kitchen = builder.kitchen;
		this.paymentMethod = builder.paymentMethod;
		this.address = builder.address;
		this.products = builder.products;
		this.active = (Objects.nonNull(builder.active)) ? builder.active : Boolean.TRUE;
		this.creationDate = builder.creationDate;
		this.updateDate = builder.updateDate;
	}
	public static Builder builder() { return new Builder(); }


	public static class Builder {
		private Long id;
		private String name;
		private BigDecimal taxFreight;
		private Kitchen kitchen;
		private List<PaymentMethod> paymentMethod;
		private Address address;
		private List<Product> products = new ArrayList<>();
		private Boolean active;
		private OffsetDateTime creationDate;
		private OffsetDateTime updateDate;

		public Builder() { }

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder taxFreight(BigDecimal taxFreight) {
			this.taxFreight = taxFreight;
			return this;
		}
		public Builder kitchen(Kitchen kitchen) {
			this.kitchen = kitchen;
			return this;
		}
		public Builder paymentMethod(List<PaymentMethod> paymentMethod) {
			this.paymentMethod = paymentMethod;
			return this;
		}
		public Builder addPaymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod.add(paymentMethod);
			return this;
		}
		public Builder removePaymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod.remove(paymentMethod);
			return this;
		}

		public Builder address(UnaryOperator<Address> func) {
			this.address = func.apply(this.address);
			return this;
		}
		public Builder address(Address address) {
			this.address = address;
			return this;
		}
		public Builder products(List<Product> products) {
			this.products = products;
			return this;
		}
		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}
		public Builder creationDate(OffsetDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		public Builder updateDate(OffsetDateTime updateDate) {
			this.updateDate = updateDate;
			return this;
		}
		/**
		 * <strong>Function:</strong> copy the properties of a restaurant that can be changed
		 * such as <strong>name, taxFreight, kitchen, address</strong>
		 * @param restaurant {@link Restaurant restaurant} that will have the values copied.
		 * @return {@link Builder Restaurant.Builder}
		 */
		public Builder copy(Restaurant restaurant) {
			this.id = (Objects.isNull(id)) ? restaurant.id : this.id;
			this.name = restaurant.name;
			this.taxFreight = restaurant.taxFreight;
			this.kitchen = restaurant.kitchen;
			this.address = restaurant.address;
			this.paymentMethod = (Objects.isNull(paymentMethod)) ? restaurant.paymentMethod : this.paymentMethod;
			this.products = (Objects.isNull(products)) ? restaurant.products : this.products;
			this.active = (Objects.isNull(active)) ? restaurant.active : this.active;
			this.creationDate = (Objects.isNull(creationDate)) ? restaurant.creationDate : this.creationDate;
			this.updateDate = (Objects.isNull(updateDate)) ? restaurant.updateDate : this.updateDate;
			return this;
		}
		public Builder clone(Restaurant restaurant) {
			this.id = restaurant.id;
			this.name = restaurant.name;
			this.taxFreight = restaurant.taxFreight;
			this.kitchen = restaurant.kitchen;
			this.paymentMethod = restaurant.paymentMethod;
			this.address = restaurant.address;
			this.products = restaurant.products;
			this.active = restaurant.active;
			this.creationDate = restaurant.creationDate;
			this.updateDate = restaurant.updateDate;
			return this;
		}
		public Restaurant build() { return new Restaurant(this); }
	}
	public Long getId() { return id; }
	public String getName() { return name; }
	public BigDecimal getTaxFreight() { return taxFreight; }
	public Kitchen getKitchen() { return kitchen; }
	public List<PaymentMethod> getPaymentMethod() { return paymentMethod; }
	public Address getAddress() { return address; }
	public List<Product> getProducts() { return products; }
	public Boolean getActive() { return active; }
	public OffsetDateTime getCreationDate() { return creationDate; }
	public OffsetDateTime getUpdateDate() { return updateDate; }
	public void activate() { this.active = true; }
	public void inactivate() { this.active = false; }
	public Long getCityId() { return this.getAddress().getCity().getId(); }
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
		return "Restaurant{" +
				"id=" + id +
				", name='" + name + '\'' +
				", taxFreight=" + taxFreight +
				", address=" + address +
				", kitchen=" + kitchen +
				", paymentMethod=" + paymentMethod +
//				", products=" + products +
				", active=" + active +
				", creationDate=" + creationDate +
				", updateDate=" + updateDate +
				'}';
	}
}
