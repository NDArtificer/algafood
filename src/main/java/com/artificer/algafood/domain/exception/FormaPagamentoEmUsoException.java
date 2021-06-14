package com.artificer.algafood.domain.exception;

public class FormaPagamentoEmUsoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public FormaPagamentoEmUsoException(String mensagem) {
		super(mensagem);
	}

	public FormaPagamentoEmUsoException(Long cozinhaId) {
		this(String.format("Não existe Forma de Pagamento com o ID %d cadastrado no banco de dados!", cozinhaId));
	}

}
