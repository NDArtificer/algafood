package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.UsuarioModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuario Restaurante")
public interface RestauranteUsuarioControllerOpenApi {

	CollectionModel<UsuarioModel> listar(Long restauranteId);

	ResponseEntity<Void> associoarUsuario(Long restauranteId, Long usuarioId);

	ResponseEntity<Void> desassocioarUsuario(Long restauranteId, Long usuarioId);

}
