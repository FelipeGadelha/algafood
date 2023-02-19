package br.com.portfolio.algafood.domain.entity;

import br.com.portfolio.algafood.config.validation.Groups;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class State implements Serializable, Functions<State> {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(groups = Groups.StateId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @Column(nullable = false)
	private String name;

	@Deprecated public State() { }
	
	public State(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
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
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + "]";
	}
}
