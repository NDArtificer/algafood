package com.artificer.algafood.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		// TODO Auto-generated constructor stub
	}
}
