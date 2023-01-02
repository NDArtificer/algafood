package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.GrupoModel;
import com.artificer.algafood.api.model.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Listar os grupos")
	CollectionModel<GrupoModel> listar();

	@Operation(summary = "Buscar um grupo por Id", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Id da grupo vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	GrupoModel buscar(@Parameter(description = "Id de um Grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Adiciona um grupo por Id", description = "Cadastro de cidade necessita de um estado váliddo e um nome de um grupo válido")
	GrupoModel adicionar(
			@RequestBody(description = "Repesentação de um novo grupo", required = true) GrupoInput grupoInput);

	@Operation(summary = "Atualiza um grupo por Id", description = "Atulização de cidade necessita de um estado váliddo e um Id e nome de um grupo válido", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Id do cidade vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	GrupoModel atualizar(@Parameter(description = "Id de um Grupo", example = "1", required = true) Long grupoId,
			@RequestBody(description = "Repesentação de um Grupo", required = true) GrupoInput grupoInput);

	@Operation(summary = "Remove um grupo por Id", responses = { @ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "Id do grupo vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	ResponseEntity<Void> remover(
			@Parameter(description = "Id de um Grupo", example = "1", required = true) Long grupoId);

}
