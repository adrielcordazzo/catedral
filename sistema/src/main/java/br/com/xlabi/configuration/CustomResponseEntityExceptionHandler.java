package br.com.xlabi.configuration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.xlabi.result.Result;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	protected Result handleError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		ex.printStackTrace();
		System.out.println("errro 500 sei la 1");

		Result result = new Result();

		return result;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();

		Result result = new Result();

		for (FieldError fieldError : fieldErrors) {
			result.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		for (ObjectError objectError : globalErrors) {
			result.addError(objectError.getObjectName(), objectError.getDefaultMessage());
		}

		System.out.println("errro 400 sei la 1" + status.name());

		return new ResponseEntity<Object>(result, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Result result = new Result();
		System.out.println("errro 400 sei la 3" + status.name());
		String unsupported = "Unsupported content type: " + ex.getContentType();
		String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());

		result.addError(unsupported, supported);
		return new ResponseEntity<Object>(result, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		ex.printStackTrace();
		Result result = new Result();
		System.out.println("errro 400 sei la 4" + status.name());
		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			String message = mostSpecificCause.getMessage();
			result.addError(exceptionName, message);
		} else {

			result.addErrorMessage(ex.getMessage());
		}
		return new ResponseEntity<Object>(result, headers, status);
	}
}