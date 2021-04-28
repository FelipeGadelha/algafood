package br.com.portfolio.algafood.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="groups_id_seq")
//	@SequenceGenerator(name="groups_id_seq", sequenceName="groups_id_seq", allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "groups_permission", 
	joinColumns = 
		@JoinColumn(name = "groups_id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id")
		)
	private List<Permission> permissions;
	
	@Deprecated
	public Group() { }

	public Group(Long id, String name, List<Permission> permissions) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
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
