package br.com.portfolio.algafood.domain.entity;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_id_seq")
//	@SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize = 1)
	@Column(name="id")
	private Long id;
	private String name;
	private String email;
	private String password;
	
	@ManyToMany
	@JoinTable(name = "users_groups", 
	joinColumns = 
		@JoinColumn(name = "users_id"),
		inverseJoinColumns = @JoinColumn(name = "groups_id")
		)
	private List<Group> groups;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name="creation_date", nullable = false)	
	private OffsetDateTime creationDate;
	
	@Deprecated
	public User() {	}

	public User(Long id, String name, String email, String password, List<Group> groups) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.groups = groups;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
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
	
}
