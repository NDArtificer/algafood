package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.GrupoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupo Usuario")
public interface UsuarioGrupoControllerOpenApi {

	CollectionModel<GrupoModel> listar(Long usuarioId);

	ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);

	ResponseEntity<Void> associar(Long usuarioId, Long grupoId);

}
