package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.artificer.algafood.api.model.PermissaoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissoes")
public interface PermissaoControllerOpenApi {

	CollectionModel<PermissaoModel> listar();

}
