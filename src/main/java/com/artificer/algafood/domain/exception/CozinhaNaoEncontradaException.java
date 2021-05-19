package com.artificer.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe Cozinha com o ID %d cadastrada no banco de dados!", cozinhaId));
	}
	
}
