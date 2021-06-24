package com.artificer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Permissao;
import com.artificer.algafood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRespository;

	@Transactional
	public Permissao buscar(Long permissaoId) {
		return permissaoRespository.findById(permissaoId).orElseThrow(() -> new NegocioException(
				String.format("Não foi encontrada a permissão com id %s no bando de dados!", permissaoId)));
	}

}
