package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.CidadeModel;
import com.artificer.algafood.api.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Listar as cidades")
	CollectionModel<CidadeModel> listar();

	@Operation(summary = "Buscar uma cidade por Id")
	CidadeModel buscar(@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId);

	@Operation(summary = "Adiciona uma cidade por Id", description = "Cadastro de cidade necessita de um estado váliddo e um nome de cidade válido")
	CidadeModel adicionar(
			@RequestBody(description = "Repesentação de uma nova Cidade", required = true) CidadeInput cidadeInput);

	@Operation(summary = "Atualiza uma cidade por Id", description = "Atulização de cidade necessita de um estado váliddo e um Id e nome de cidade válido")
	CidadeModel atualizar(@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId,
			@RequestBody(description = "Repesentação de uma Cidade", required = true) CidadeInput cidadeInput);

	@Operation(summary = "Remove uma cidade por Id")
	ResponseEntity<Void> remover(
			@Parameter(description = "Id de uma Cidade", example = "1", required = true) Long cidadeId);
}