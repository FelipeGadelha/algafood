package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;

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
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_id_seq")
//	@SequenceGenerator(name="city_id_seq", sequenceName="city_id_seq", allocationSize = 1)
	@Column(name="id")	
	private Long id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;
	
	@Deprecated
	public City() {	}

	public City(Long id, String name, State state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
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
		City other = (City) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
	}
	
	

}
