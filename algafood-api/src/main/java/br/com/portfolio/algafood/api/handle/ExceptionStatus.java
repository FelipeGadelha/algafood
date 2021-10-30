package br.com.portfolio.algafood.api.handle;

public enum ExceptionStatus {
	
	MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	INVALID_FORMAT("/invalid-format", "Invalid format"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	ILLEGAL_STATE("/illegal-state", "Illegal State"),
	ILLEGAL_ARGUMENT("/illegal-argument", "Illegal argument"),
	ARGUMENT_NOT_VALID("/argument-not-valid", "Argument not valid"),
	BUSINESS_ERROR("/business-error", "Business rule violation");
	
	private String title;
	private String uri;
	
	ExceptionStatus(String path, String title) {
		this.uri = "https://localhost:8080/api/algafood" + path;
		this.title = title;
	}

	public String getUri() {
		return uri;
	}
	
	public String getTitle() {
		return title;
	}
}
