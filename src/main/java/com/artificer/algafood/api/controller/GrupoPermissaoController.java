package com.artificer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.PermissaoModelConverter;
import com.artificer.algafood.api.model.PermissaoModel;
import com.artificer.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelConverter modelConverter;

	@GetMapping
	public List<PermissaoModel> listar(@PathVariable Long grupoId) {
		var grupo = cadastroGrupo.buscar(grupoId);
		return modelConverter.toColletionModel(grupo.getPermissoes());
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
	}

}
