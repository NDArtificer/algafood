package com.artificer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.model.Pedido;

@Service
public class AlteracaoPedidoService {

	@Autowired
	private CadastroPedidoService pedidoSerivce;

	@Transactional
	public void confirmar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.confirmar();

	}

	@Transactional
	public void cancelar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.cancelar();

	}

	@Transactional
	public void entregar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.entregar();

	}

}
