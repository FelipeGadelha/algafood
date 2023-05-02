package br.com.portfolio.algafood.domain.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	
	@ManyToMany
	@JoinTable(name = "users_groups", 
	joinColumns = @JoinColumn(name = "users_id"),
		inverseJoinColumns = @JoinColumn(name = "groups_id")
		)
	private List<Group> groups;
	
	@CreationTimestamp
	@Column(name="creation_date", nullable = false)	
	private OffsetDateTime creationDate;
	
	@Deprecated
	public User() {	}

	private User(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
		this.password = builder.password;
		this.groups = builder.groups;
		this.creationDate = builder.creationDate;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private String email;
		private String password;
		private List<Group> groups;
		private OffsetDateTime creationDate;
		private Builder() {}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		public Builder groups(List<Group> groups) {
			this.groups = groups;
			return this;
		}
		public Builder addGroup(Group group) {
			this.groups.add(group);
			return this;
		}
		public Builder removeGroup(Group group) {
			this.groups.remove(group);
			return this;
		}
		public Builder clone(User user) {
			this.id = user.id;
			this.name = user.name;
			this.email = user.email;
			this.password = user.password;
			this.groups = user.groups;
			this.creationDate = user.creationDate;
			return this;
		}
		public Builder copy(User user) {
			this.id = (Objects.isNull(id)) ? user.id : this.id;
			this.name = user.name;
			this.email = user.email;
			this.password = (Objects.isNull(password)) ? user.password : this.password;
			this.groups = (Objects.isNull(groups)) ? user.groups : this.groups;
			this.creationDate = (Objects.isNull(creationDate)) ? user.creationDate : this.creationDate;
			return this;
		}
		public User build() { return new User(this); }
    }
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public OffsetDateTime getCreationDate() { return creationDate; }

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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", groups="
				+ groups + ", creationDate=" + creationDate + "]";
	}
	public <R> R map(Function<User, R> func) {
		return func.apply(this);
	}
	public void also(Consumer<User> consumer) {
		consumer.accept(this);
	}
	public void run(Supplier<User> supplier) {
		supplier.get();
	}
	public User let(UnaryOperator<User> op) {
		return op.apply(this);
	}
}
