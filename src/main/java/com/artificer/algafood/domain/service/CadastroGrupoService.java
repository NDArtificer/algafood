package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.EntidadeEmUsoException;
import com.artificer.algafood.domain.exception.GrupoNaoEncontradaException;
import com.artificer.algafood.domain.model.Grupo;
import com.artificer.algafood.domain.model.Permissao;
import com.artificer.algafood.domain.repository.GrupoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Não foi encontrado um grupo com Id %s na base de dados!";
	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroPermissaoService cadastroPermissao;

	@Transactional
	public Grupo buscar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradaException(grupoId));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscar(grupoId);
		Permissao permissao = cadastroPermissao.buscar(permissaoId);

		grupo.adicionarPermissao(permissao);

	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscar(grupoId);
		Permissao permissao = cadastroPermissao.buscar(permissaoId);

		grupo.removerPermissao(permissao);

	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradaException(grupoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

}
