package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.EntidadeEmUsoException;
import com.artificer.algafood.domain.exception.EstadoNaoEncontradaException;
import com.artificer.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.repository.FormaPagamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_FORMA_PAGAMENTO_EM_USO = "A Forma de Pagamento não pode ser removida, pois está em uso!";
	@Autowired
	private FormaPagamentoRepository formaPagamentoRespository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRespository.save(formaPagamento);
	}

	@Transactional
	public FormaPagamento buscar(Long formaPagamentoId) {
		// TODO Auto-generated method stub
		return formaPagamentoRespository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}

	@Transactional
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRespository.deleteById(formaPagamentoId);
			formaPagamentoRespository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(formaPagamentoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
		}

	}

}
