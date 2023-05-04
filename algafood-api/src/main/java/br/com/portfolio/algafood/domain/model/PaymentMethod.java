package br.com.portfolio.algafood.domain.model;

import java.io.Serializable;

import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "payment_method")
public class PaymentMethod implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
	
	@Deprecated
	public PaymentMethod() { }
	
	public PaymentMethod(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() { return id; }
	public String getDescription() { return description; }
	public Instant getUpdatedAt() { return updatedAt; }

//	public void setDescription(String description) {
//		this.description = description;
//	}


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
		PaymentMethod other = (PaymentMethod) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentMethod{" +
			"id=" + id +
			", description='" + description + '\'' +
			", updatedAt=" + updatedAt +
			'}';
	}
}
