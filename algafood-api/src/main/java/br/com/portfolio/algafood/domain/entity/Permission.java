package br.com.portfolio.algafood.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permission_id_seq")
//	@SequenceGenerator(name="permission_id_seq", sequenceName="permission_id_seq", allocationSize = 1)
	@Column(name="id")	
	private Long id;
	
	private String name;
	
	private String description;

	@Deprecated
	public Permission() { }
	
	public Permission(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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
		Permission other = (Permission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
	
	

}
