package com.artificer.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.artificer.algafood.api.model.FormaPagamentoModel;
import com.artificer.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@Operation(summary = "Lista as formas de pagamento")
	CollectionModel<FormaPagamentoModel> listar();

	@Operation(summary = "Busca uma forma de pagamento por Id", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Id da forma de pagamento inválido", content = {
					@Content(schema = @Schema(ref = "Problem")) }),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
					@Content(schema = @Schema(ref = "Problem")) }) })
	FormaPagamentoModel buscar(
			@Parameter(description = "Id de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

	@Operation(summary = "Cadastra uma forma de pagamento", responses = {
			@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada") })
	FormaPagamentoModel adicionar(
			@RequestBody(description = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Atualiza uma forma de pagamento por Id", responses = {
			@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
					@Content(schema = @Schema(ref = "Problem")) }) })
	FormaPagamentoModel atualizar(
			@Parameter(description = "Id de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
			@RequestBody(description = "Representação de uma forma de pagamento com os novos dados", required = true) FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Exclui uma forma de pagamento por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Forma de pagamento excluída"),
			@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = {
					@Content(schema = @Schema(ref = "Problem")) }) })
	ResponseEntity<Void> remover(
			@Parameter(description = "Id de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
