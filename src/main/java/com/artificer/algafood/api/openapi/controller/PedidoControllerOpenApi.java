package com.artificer.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.artificer.algafood.api.model.PedidoModel;
import com.artificer.algafood.api.model.PedidoResumoModel;
import com.artificer.algafood.api.model.input.PedidoInput;
import com.artificer.algafood.domain.repository.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedido")
public interface PedidoControllerOpenApi {
	PagedModel<PedidoResumoModel> listar(PedidoFilter filtro, Pageable pageable);

	PedidoModel adicionar(PedidoInput pedidoInput);

	PedidoModel buscar(String codigoPedido);

}
