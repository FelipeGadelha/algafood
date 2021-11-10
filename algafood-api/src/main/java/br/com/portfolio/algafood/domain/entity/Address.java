package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "address_cep")
	private String cep;
	@Column(name = "address_place")
	private String place;
	@Column(name = "address_number")
	private String number;
	@Column(name = "address_complement")
	private String complement;
	@Column(name = "address_district")
	private String district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer"})//, "handler"})
	@JoinColumn(name = "address_city_id")
	private City city;
	/**
	 * @deprecated Constructor used by Hibernate;
	 */
	@Deprecated
	public Address() { }

	public Address(Builder builder) {
		this.cep = builder.cep;
		this.place = builder.place;
		this.number = builder.number;
		this.complement = builder.complement;
		this.district = builder.district;
		this.city = builder.city;
	}

	public static Builder builder() { return new Builder(); }
	public static class Builder {

		private String cep;
		private String place;
		private String number;
		private String complement;
		private String district;
		private City city;

		public Builder() { }

		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}
		public Builder place(String place) {
			this.place = place;
			return this;
		}
		public Builder number(String number) {
			this.number = number;
			return this;
		}
		public Builder complement(String complement) {
			this.complement = complement;
			return this;
		}
		public Builder district(String district) {
			this.district = district;
			return this;
		}
		public Builder city(City city) {
			this.city = city;
			return this;
		}
		public Builder clone(Address address) {
			this.cep = address.getCep();
			this.place = address.getPlace();
			this.number = address.getNumber();
			this.complement = address.getComplement();
			this.district = address.getDistrict();
			this.city = address.getCity();
			return this;
		}
		public Address build() { return new Address(this); }
	}
	public String getCep() {
		return cep;
	}
	public String getPlace() {
		return place;
	}
	public String getNumber() {
		return number;
	}
	public String getComplement() {
		return complement;
	}
	public String getDistrict() {
		return district;
	}
	public City getCity() {
		return city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((complement == null) ? 0 : complement.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
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
		Address other = (Address) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (complement == null) {
			if (other.complement != null)
				return false;
		} else if (!complement.equals(other.complement))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [cep=" + cep + ", place=" + place + ", number=" + number + ", complement=" + complement
				+ ", district=" + district + "]";
	}
}
