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

import com.artificer.algafood.api.converter.model.GrupoModelConverter;
import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.api.utils.ApiLinks;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelConverter modelConverter;

	@Autowired
	private ApiLinks apiLinks;

	@CheckSecurity.UsuariosGrupoPermissoes.Readable
	@GetMapping
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscar(usuarioId);
		CollectionModel<GrupoModel> gruposModel = modelConverter.toCollectionModel(usuario.getGrupos())
				.add(apiLinks.linkToAddGrupoUsuario(usuarioId, "Add"));
		gruposModel.getContent().forEach(grupoModel -> {
			grupoModel.add(apiLinks.linkToRemoveGrupoUsuario(usuarioId, grupoModel.getId(), "Remover"));
		});

		return gruposModel;
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@PutMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@DeleteMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}

}
