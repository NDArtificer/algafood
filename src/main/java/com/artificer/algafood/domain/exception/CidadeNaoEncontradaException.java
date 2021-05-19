package com.artificer.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Não existe Cidade com o ID %d cadastrada no banco de dados!", cidadeId));
	}
	
}
