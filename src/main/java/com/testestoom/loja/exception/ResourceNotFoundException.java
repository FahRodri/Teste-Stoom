package com.testestoom.loja.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s não encontrado com %s: %s", resource, field, value));
    }
}
