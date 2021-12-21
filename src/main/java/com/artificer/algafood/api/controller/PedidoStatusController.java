package com.artificer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.algafood.core.security.CheckSecurity;
import com.artificer.algafood.domain.service.AlteracaoPedidoService;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class PedidoStatusController {

	@Autowired
	private AlteracaoPedidoService alteracaoService;

	@CheckSecurity.Pedidos.Manageable
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
		alteracaoService.confirmar(codigoPedido);
		return ResponseEntity.noContent().build();

	}

	@CheckSecurity.Pedidos.Manageable
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
		alteracaoService.cancelar(codigoPedido);
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Pedidos.Manageable
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
		alteracaoService.entregar(codigoPedido);
		return ResponseEntity.noContent().build();
	}

}
