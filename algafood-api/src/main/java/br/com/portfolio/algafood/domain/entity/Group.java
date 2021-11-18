package br.com.portfolio.algafood.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;

@Entity
@Table(name = "groups")
public class Group {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "groups_permission", 
	joinColumns = 
		@JoinColumn(name = "groups_id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id")
		)
	private Set<Permission> permissions = new HashSet<>();
	
	@Deprecated
	public Group() { }

	public Group(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.permissions = builder.permissions;
	}
	public static Builder builder() { return new Builder(); }
	public static class Builder {
		private Long id;
		private String name;
		private Set<Permission> permissions = new HashSet<>();

		public Builder() { }

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder permissions(Set<Permission> permissions) {
			this.permissions = permissions;
			return this;
		}
		public Builder addPermissions(Permission permission) {
			this.permissions.add(permission);
			return this;
		}
		public Builder removePermissions(Permission permission) {
			this.permissions.remove(permission);
			return this;
		}
		public Builder clone(Group group) {
			this.id = group.id;
			this.name = group.name;
			this.permissions = group.permissions;
			return this;
		}

		public Builder copy(Group group) {
			this.id = group.id;
			this.name = group.name;
			this.permissions = (Objects.isNull(permissions)) ? group.permissions : this.permissions;
			return this;
		}

		public Group build() { return new Group(this); }

	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<Permission> getPermissions() {
		return permissions;
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
	}
}
