package br.com.portfolio.algafood.domain.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Permission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	@Deprecated
	public Permission() { }
	
	private Permission(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
	}
	public static Builder builder() { return new Builder(); }

	public static class Builder {
		private Long id;
		private String name;
		private String description;
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		public Builder clone(Permission permission) {
			this.id = permission.id;
			this.name = permission.name;
			this.description = permission.description;
			return this;
		}
		public Builder copy(Permission permission) {
			this.id = (Objects.isNull(this.id)) ? permission.id : this.id;
			this.name = permission.name;
			this.description = permission.description;
			return this;
		}
		public Permission build() { return new Permission(this); }
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
