package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários responsáveis associados a restaurante", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }), })
	CollectionModel<UsuarioModel> listar(
			@Parameter(description = "Id do restaurante", example = "1", required = true) Long restauranteId);

	@Operation(summary = "Associação de restaurante com usuário responsável", responses = {
			@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }), })
	ResponseEntity<Void> associoarUsuario(
			@Parameter(description = "Id do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "Id do usuário", example = "1", required = true) Long usuarioId);

	@Operation(summary = "Desassociação de restaurante com usuário responsável", responses = {
			@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }), })
	ResponseEntity<Void> desassocioarUsuario(
			@Parameter(description = "Id do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "Id do usuário", example = "1", required = true) Long usuarioId);

}
