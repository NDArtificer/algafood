package com.artificer.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Não existe Restaurante com o ID %d cadastrado no banco de dados!", restauranteId));
	}
	
}
