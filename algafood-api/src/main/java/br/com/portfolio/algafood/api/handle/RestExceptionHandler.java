package br.com.portfolio.algafood.api.handle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
private static final String ERROR_FIELD = "Check the error field(s)";
private static final String DOCUMENTATION = ", Check the Documentation";

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (Objects.isNull(body)) {
			body = ExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.title(status.getReasonPhrase() + DOCUMENTATION)
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();			
		} else if (body instanceof String) {
			body = ExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.title((String) body)
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(
			NullPointerException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(
			EntityNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(
			IllegalArgumentException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(StaleObjectStateException.class)
	public ResponseEntity<Object> handleStaleObjectStateException(
			StaleObjectStateException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
//	@ExceptionHandler(EntityInUseException.class)
//	public ResponseEntity<BadRequestExceptionDetails> handleEntityInUseException(
//			EntityInUseException exception) {
//
//		return new ResponseEntity<>(
//				BadRequestExceptionDetails.builder()
//					.timestamp(OffsetDateTime.now())
//					.status(HttpStatus.BAD_REQUEST.value())
//					.title("Bad Request Exception, Check the Documentation")
//					.details(exception.getMessage())
//					.developerMessage(exception.getClass().getName())
//					.build(),
//				HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
		return handleExceptionInternal(ex, exceptionReplace(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();	
		
    	List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
    	
    	Map<String, Set<String>> map = fieldErros.stream().collect(Collectors.groupingBy(FieldError::getField,
				Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
    	
    	if (map.isEmpty()) {
    		map = globalErrors.stream().collect(Collectors.groupingBy(ObjectError::getCode,
    				Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toSet())));			
		}
		return new ResponseEntity<>(ValidationExceptionDetails
				.builder().timestamp(OffsetDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title(HttpStatus.BAD_REQUEST.getReasonPhrase() + DOCUMENTATION)
				.details(ERROR_FIELD)
				.developerMessage(exception.getClass().getName())
				.errors(map)
				.build(), headers, HttpStatus.BAD_REQUEST);		
	}
	
	private String exceptionReplace(Exception ex) {
		List<String> list = new ArrayList<>();
		Arrays.asList(ex.getClass().getSimpleName().split("")).forEach(f -> {
			if (f.matches("[A-Z]")) list.add(" " + f);
			else list.add(f);
		});
		return list.stream().collect(Collectors.joining()).trim() + DOCUMENTATION;
	}
	
	
}
