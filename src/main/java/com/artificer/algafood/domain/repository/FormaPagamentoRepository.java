package com.artificer.algafood.domain.repository;

import java.util.List;

import com.artificer.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();

	FormaPagamento buscar(Long id);

	FormaPagamento salvar(FormaPagamento restaurante);

	void remover(FormaPagamento restaurante);

	
}
