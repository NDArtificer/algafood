package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.PermissaoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupo Permissao")
public interface GrupoPermissaoControllerOpenApi {

	CollectionModel<PermissaoModel> listar(Long grupoId);

	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

}
