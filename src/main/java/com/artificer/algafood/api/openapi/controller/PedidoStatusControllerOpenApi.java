package com.artificer.algafood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Status Pedido")
public interface PedidoStatusControllerOpenApi {
	ResponseEntity<Void> confirmar(String codigoPedido);

	ResponseEntity<Void> cancelar(String codigoPedido);

	ResponseEntity<Void> entregar(String codigoPedido);

}
