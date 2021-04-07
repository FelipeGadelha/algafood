package br.com.portfolio.algafood.domain.service;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Result<T> {

	private static final Result<?> EMPTY = new Result<>();
	
	private T data;
	private String title;
	private String details;
	private OffsetDateTime timestamp;
	
	public Result() {
		data = null;
	}
	
	public Result(T data) {
		this.data = data;
		this.title = null;
		this.details = null;
		this.timestamp = null;
	}
	
    public static<T> Result<T> empty() {
        @SuppressWarnings("unchecked")
        Result<T> t = (Result<T>) EMPTY;
        return t;
    }
	private Result(Builder<T> builder) {
		data = null;
		this.title = builder.title;
		this.details = builder.details;
		this.timestamp = OffsetDateTime.now();
	}
	
	public boolean hasError() {
		return Objects.nonNull(title);
	}
	
	public T getData() {
		return data;
	}

	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}

	public String getTimestamp() {
		if (timestamp == null) return null;
		return timestamp.toString();
	}
	
	public static class Builder<T> {

		private String title;
		private String details;

		public Builder() {
		}

		public Builder<T> title(String title) {
			this.title = title;
			return this;
		}

		public Builder<T> details(String details) {
			this.details = details;
			return this;
		}

		public Result<T> build() {
			return new Result<>(this);
		}
	}

	@Override
	public String toString() {
		return "title= " + title + ", details= " + details
				+ ", timestamp= " + timestamp;
	}
}
