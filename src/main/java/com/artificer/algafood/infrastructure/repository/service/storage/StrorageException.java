package com.artificer.algafood.infrastructure.repository.service.storage;

public class StrorageException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public StrorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public StrorageException(String message) {
		super(message);
	}

	
	
}
