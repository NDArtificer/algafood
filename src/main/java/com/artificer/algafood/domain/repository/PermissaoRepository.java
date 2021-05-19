package com.artificer.algafood.domain.repository;

import java.util.List;

import com.artificer.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> listar();

	Permissao buscar(Long id);

	Permissao salvar(Permissao restaurante);

	void remover(Permissao restaurante);

}
