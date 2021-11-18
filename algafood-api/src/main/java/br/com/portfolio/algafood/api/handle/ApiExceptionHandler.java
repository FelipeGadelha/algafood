package br.com.portfolio.algafood.api.handle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.portfolio.algafood.domain.exception.BusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String ERROR_FIELD = "Check the error field(s)";
	private static final String DOCUMENTATION = ", Check the Documentation";

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	private void activateDirectFieldAccess(DataBinder dataBinder) { dataBinder.initDirectFieldAccess(); }

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
		} else if (body instanceof ExceptionStatus) {
			ExceptionStatus exStatus = (ExceptionStatus) body;
			body = ExceptionDetails
					.builder()
					.timestamp(OffsetDateTime.now())
					.status(status.value())
					.type(exStatus.getUri())
					.title(exStatus.getTitle())
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionStatus.BUSINESS_ERROR, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
		return handleExceptionInternal(ex,  ExceptionStatus.RESOURCE_NOT_FOUND, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionStatus.BUSINESS_ERROR, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionStatus.RESOURCE_NOT_FOUND, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@Override
		protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
		
		String details = String.format("the resource '%s' that you tried to access does not exist", ex.getRequestURL());
		return new ResponseEntity<>(
		createExceptionDetailsBuilder(
				ex, 
				HttpStatus.NOT_FOUND, 
				ExceptionStatus.RESOURCE_NOT_FOUND, 
				details),
		headers, HttpStatus.NOT_FOUND);

		}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
//		String detail = String.format("Ignored field '%s' encountered; mapper configured not to allow this", "");
		return new ResponseEntity<>(
				createExceptionDetailsBuilder(
						ex, 
						HttpStatus.BAD_REQUEST, 
						ExceptionStatus.ILLEGAL_ARGUMENT, 
						ex.getMessage()),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String type = ex.getRequiredType().getSimpleName();
		String details = String.format("the URL parameter '%s' received the value '%s', which is of an invalid type, "
				+ "correct and enter a value compatible with the '%s' type.", ex.getName(), ex.getValue(), type);
		return new ResponseEntity<>(
				createExceptionDetailsBuilder(
					ex, 
					HttpStatus.BAD_REQUEST, 
					ExceptionStatus.INVALID_PARAMETER, 
					details), 
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StaleObjectStateException.class)
	public ResponseEntity<Object> handleStaleObjectStateException(
			StaleObjectStateException ex, WebRequest request) {
		return handleExceptionInternal(ex,  ExceptionStatus.ILLEGAL_STATE, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
		return handleExceptionInternal(ex, ExceptionStatus.ILLEGAL_STATE, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();	
		
    	List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
    	
    	Map<String, Set<String>> map = fieldErros.stream().collect(Collectors.groupingBy(FieldError::getField,
				Collectors.mapping(fieldError -> messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()), Collectors.toSet())));
    	
    	if (map.isEmpty()) {
    		map = globalErrors.stream().collect(Collectors.groupingBy(ObjectError::getCode,
    				Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toSet())));			
		}
		return new ResponseEntity<>(ValidationExceptionDetails
				.builder().timestamp(OffsetDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.type(ExceptionStatus.ARGUMENT_NOT_VALID.getUri())
				.title(ExceptionStatus.ARGUMENT_NOT_VALID.getTitle() + DOCUMENTATION)
				.details(ERROR_FIELD)
				.developerMessage(exception.getClass().getName())
				.errors(map)
				.build(), headers, HttpStatus.BAD_REQUEST);		
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
		} else if(rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		return new ResponseEntity<>(
				createExceptionDetailsBuilder(
						ex, 
						HttpStatus.BAD_REQUEST, 
						ExceptionStatus.MESSAGE_NOT_READABLE, 
						"The request body is invalid, check syntax error."),
				headers, status);
	}
	
	

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		ExceptionStatus exceptionStatus = ExceptionStatus.MESSAGE_NOT_READABLE;
		String detail = String.format("This property '%s' does no exist. "
				+ "Correct or remove this property and try again.", path);

		ExceptionDetails exceptionDetails = createExceptionDetailsBuilder(ex, status, exceptionStatus, detail);
		
		return handleExceptionInternal(ex, exceptionDetails, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath()
				.stream()
				.map(m -> m.getFieldName())
				.collect(Collectors.joining("."));
		String detail = String.format(
				"property %s was given the value '%s' which is of an invalid type. "
				+ "Correct to a value compatible with type %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		return new ResponseEntity<>(
				createExceptionDetailsBuilder(
					ex, 
					HttpStatus.BAD_REQUEST, 
					ExceptionStatus.INVALID_FORMAT, 
					detail), 
				headers, status);
	}
	
	private ExceptionDetails createExceptionDetailsBuilder(Exception ex, HttpStatus status,
			ExceptionStatus exceptionStatus, String detail) {
		
		return ExceptionDetails.builder()
			.timestamp(OffsetDateTime.now())
			.status(status.value())
			.type(exceptionStatus.getUri())
			.title(exceptionStatus.getTitle() + DOCUMENTATION)
			.details(detail)
			.developerMessage(ex.getClass().getName())
			.build();
	}

	private String exceptionReplace(Exception ex) {
		List<String> list = new ArrayList<>();
		Arrays.asList(ex.getClass().getSimpleName().split("")).forEach(f -> {
			if (f.matches("[A-Z]")) list.add(" " + f);
			else list.add(f);
		});
		return list.stream().collect(Collectors.joining()).trim() + DOCUMENTATION;
	}
	
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(Reference::getFieldName)
			.collect(Collectors.joining("."));
	}
}
