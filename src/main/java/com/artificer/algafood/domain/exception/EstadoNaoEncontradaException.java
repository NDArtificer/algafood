package com.artificer.algafood.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	
	public EstadoNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe Estado com o ID %d cadastrado no banco de dados!", estadoId));
	}
	
}
