package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.PermissaoModelConverter;
import com.artificer.algafood.api.model.PermissaoModel;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Permissao;
import com.artificer.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private PermissaoModelConverter permissaoModelConverter;

	@CheckSecurity.UsuariosGrupoPermissoes.Readable
	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		List<Permissao> todasPermissoes = permissaoRepository.findAll();

		return permissaoModelConverter.toCollectionModel(todasPermissoes);
	}

}
