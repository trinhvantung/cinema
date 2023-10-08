package vn.trinhtung.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.trinhtung.dto.ErrorResponseDTO;
import vn.trinhtung.exception.ApplicationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Map<String, String> handleBindException(BindException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApplicationException.class)
	public ErrorResponseDTO handleApplicationException(ApplicationException e) {

		return new ErrorResponseDTO(e.getError(), e.getMessage());
	}
}
