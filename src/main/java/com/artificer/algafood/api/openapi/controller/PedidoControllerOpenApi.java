package com.artificer.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.artificer.algafood.api.model.PedidoModel;
import com.artificer.algafood.api.model.PedidoResumoModel;
import com.artificer.algafood.api.model.input.PedidoInput;
import com.artificer.algafood.domain.repository.filter.PedidoFilter;

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
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {

	@Operation(summary = "Pesquisa os pedidos", parameters = {
			@Parameter(in = ParameterIn.QUERY, name = "clienteId", description = "Id do cliente para filtro da pesquisa", example = "1", schema = @Schema(type = "integer")),
			@Parameter(in = ParameterIn.QUERY, name = "restauranteId", description = "Id do restaurante para filtro da pesquisa", example = "1", schema = @Schema(type = "integer")),
			@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data/hora de criação inicial para filtro da pesquisa", example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
			@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data/hora de criação final para filtro da pesquisa", example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time")) })

	@Parameter(in = ParameterIn.QUERY, name = "page", description = "Número da página (0 ... N)", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "size", description = "Quantidade de Páginas", schema = @Schema(type = "integer", defaultValue = "10"))
	@Parameter(in = ParameterIn.QUERY, name = "sort", description = "Ordenação: asc ou desc", examples = {
			@ExampleObject("nome"), @ExampleObject("nome,asc"), @ExampleObject("nome,desc") })
	PagedModel<PedidoResumoModel> listar(@Parameter(hidden = true) PedidoFilter filtro,
			@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Registra um pedido", responses = {
			@ApiResponse(responseCode = "201", description = "Pedido registrado"), })
	PedidoModel adicionar(
			@RequestBody(description = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

	@Operation(summary = "Busca um pedido por código", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
					@Content(schema = @Schema(ref = "Problem")) }), })
	PedidoModel buscar(
			@Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String codigoPedido);

}
