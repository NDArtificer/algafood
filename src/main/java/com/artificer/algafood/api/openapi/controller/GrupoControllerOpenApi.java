package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.api.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupo")
public interface GrupoControllerOpenApi {
	CollectionModel<GrupoModel> listar();

	GrupoModel buscar(Long grupoId);

	GrupoModel adicionar(GrupoInput grupoInput);

	GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);

	ResponseEntity<Void> remover(Long grupoId);

}
