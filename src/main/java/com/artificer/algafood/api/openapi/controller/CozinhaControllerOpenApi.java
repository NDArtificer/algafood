package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.artificer.algafood.api.model.CozinhaModel;
import com.artificer.algafood.api.model.input.CozinhaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@Operation(summary = "Lista as cozinhas")
	@Parameter(in = ParameterIn.QUERY, name = "page", description = "Número da página (0 ... N)", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "size", description = "Quantidade de Páginas", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "sort", description = "Ordenação: asc ou desc", examples = {
			@ExampleObject("nome"), @ExampleObject("nome,asc"), @ExampleObject("nome,desc") })
	PagedModel<CozinhaModel> listar(@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca uma Cozinha por Id", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Id da Cozinha vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))),
			@ApiResponse(responseCode = "400", description = "Id da Cozinha vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	CozinhaModel buscar(@Parameter(description = "Id de uma Cozinha", example = "1", required = true) Long cozinhaId);

	@Operation(summary = "Adiciona uma Cozinha", description = "Cadastro de Cozinha necessita de um nome de Cozinha válido", responses = {
			@ApiResponse(responseCode = "201", description = "Cozinha cadastrada") })
	CozinhaModel adicionar(
			@RequestBody(description = "Repesentação de uma nova Cozinha", required = true) CozinhaInput cozinhaInput);

	@Operation(summary = "Atualiza uma Cozinha por Id", description = "Atulização de Cozinha necessita de um Cozinha válido", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Id da Cozinha vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	CozinhaModel atualizar(@Parameter(description = "Id de uma Cozinha", example = "1", required = true) Long cozinhaId,
			@RequestBody(description = "Repesentação de uma nova Cozinha", required = true) CozinhaInput cozinhaInput);

	@Operation(summary = "Remove uma Cozinha por Id", responses = {
			@ApiResponse(responseCode = "204", description = "Cozinha excluída"),
			@ApiResponse(responseCode = "404", description = "Id da Cozinha vazio ou inválido.", content = @Content(schema = @Schema(ref = "Problem"))) })
	ResponseEntity<Void> remover(
			@Parameter(description = "Id de uma Cozinha", example = "1", required = true) Long cozinhaId);
}
