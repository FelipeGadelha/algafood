package br.com.portfolio.algafood.domain.model;

public enum OrderStatusType {
	CREATED("Criado"),
	CONFIRMED("Confirmado"),
	DELIVERED("Entregue"),
	CANCELED("Cancelado");

	private String description;
	OrderStatusType(String description) { this.description = description; }
	public String getDescription() { return description; }
}
