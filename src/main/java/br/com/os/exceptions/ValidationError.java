package br.com.os.exceptions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	
	public ValidationError() {
		super();
	}

	public ValidationError(LocalDateTime timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	public List<FieldMessage> getErro() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}
