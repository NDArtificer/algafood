package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.api.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Listar as cidades")
	CollectionModel<CidadeModel> listar();

	@Operation(summary = "Buscar uma cidade por Id", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Id da cidade vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	CidadeModel buscar(@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId);

	@Operation(summary = "Adiciona uma cidade por Id", description = "Cadastro de cidade necessita de um estado váliddo e um nome de cidade válido")
	CidadeModel adicionar(
			@RequestBody(description = "Repesentação de uma nova Cidade", required = true) CidadeInput cidadeInput);

	@Operation(summary = "Atualiza uma cidade por Id", description = "Atulização de cidade necessita de um estado váliddo e um Id e nome de cidade válido", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Id da cidade vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	CidadeModel atualizar(@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId,
			@RequestBody(description = "Repesentação de uma Cidade", required = true) CidadeInput cidadeInput);

	@Operation(summary = "Remove uma cidade por Id", responses = { @ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "Id da cidade vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	ResponseEntity<Void> remover(
			@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId);
}