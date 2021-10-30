package br.com.portfolio.algafood.api.handle;

public class BadRequestExceptionDetails extends ExceptionDetails{
	
	public BadRequestExceptionDetails(Builder builder) {
		super(builder);
	}

	public static Builder builder() {
        return new Builder();
    }
	
	public static class Builder extends ExceptionDetails.Builder<Builder> {
		
		protected Builder() {
			super();
		}

		@Override
		public Builder getThis() {
			return this;
		}
		
		@Override
		public BadRequestExceptionDetails build() {
            return new BadRequestExceptionDetails(this);
        }
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
