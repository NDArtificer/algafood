package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.api.converter.input.GrupoInputConverter;
import com.artificer.algafood.api.converter.model.GrupoModelConverter;
import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.api.model.input.GrupoInput;
import com.artificer.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.model.Grupo;
import com.artificer.algafood.domain.repository.GrupoRepository;
import com.artificer.algafood.domain.service.CadastroGrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroGrupoService grupoService;

	@Autowired
	private GrupoModelConverter modelConverter;

	@Autowired
	private GrupoInputConverter inputConverter;

	@CheckSecurity.UsuariosGrupoPermissoes.Readable
	@GetMapping
	public CollectionModel<GrupoModel> listar() {
		return modelConverter.toCollectionModel(grupoRepository.findAll());
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Readable
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		return modelConverter.toModel(grupoService.buscar(grupoId));
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = inputConverter.toDomainObject(grupoInput);
		return modelConverter.toModel(grupoService.salvar(grupo));
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = grupoService.buscar(grupoId);

		inputConverter.copyToDomainObject(grupoInput, grupoAtual);
		return modelConverter.toModel(grupoService.salvar(grupoAtual));
	}

	@CheckSecurity.UsuariosGrupoPermissoes.Editble
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long grupoId) {
		grupoService.excluir(grupoId);
		return ResponseEntity.noContent().build();
	}

}
