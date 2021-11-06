package br.com.portfolio.algafood.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;
	
	@Deprecated
	public City() {	}

	public City(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.state = builder.state;
	}

	public static Builder builder() { return new Builder(); }
	public static class Builder {
		private Long id;
		private String name;
		private State state;

		public Builder() { }

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder state(State state) {
			this.state = state;
			return this;
		}
		public Builder copy(City city) {
			this.id = (Objects.isNull(id)) ? city.id : this.id;
			this.name = city.name;
			this.state = city.state;
			return this;
		}
		public Builder clone(City city) {
			this.id = city.id;
			this.name = city.name;
			this.state = city.state;
			return this;
		}
		public City build() { return new City(this); }
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public State getState() {
		return state;
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
