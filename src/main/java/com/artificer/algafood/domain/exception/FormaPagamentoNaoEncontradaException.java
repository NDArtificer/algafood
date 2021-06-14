package com.artificer.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe Forma de Pagamento com o ID %d cadastrado no banco de dados!", cozinhaId));
	}

}
