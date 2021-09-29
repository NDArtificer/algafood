package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.model.PermissaoModelConverter;
import com.artificer.algafood.api.model.PermissaoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelConverter modelConverter;

	@Autowired
	private ApiLinks apiLinks;
	
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		var grupo = cadastroGrupo.buscar(grupoId);
		CollectionModel<PermissaoModel> permissoesModel = modelConverter.toCollectionModel(grupo.getPermissoes());
		
		permissoesModel.getContent().forEach(permissaoModel -> {
			permissaoModel.add(apiLinks.linkToRemoveGrupoPermissoes(grupoId, permissaoModel.getId(),"Remove"));
		});
		
		return permissoesModel.add(apiLinks.linkToAddGrupoPermissoes(grupoId, "Add"));
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

}
