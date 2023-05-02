package br.com.portfolio.algafood.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonRootName("gastronomy")
@Entity
public class Kitchen implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Column(nullable = false)
	private String name;

	@JsonManagedReference @OneToMany(mappedBy = "kitchen", cascade = CascadeType.ALL)
	private List<Restaurant> restaurants = new ArrayList<>();
	
	@Deprecated
	public Kitchen() {	}

	public Kitchen(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Kitchen(Long id, String name, List<Restaurant> restaurants) {
		this.id = id;
		this.name = name;
		this.restaurants = restaurants;
	}
	public Long getId() { return id; }
	public String getName() {
		return name;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
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
		Kitchen other = (Kitchen) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Kitchen [id=" + id + ", name=" + name + "]";
	}
}
