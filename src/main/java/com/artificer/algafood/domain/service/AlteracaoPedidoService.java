package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlteracaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CadastroPedidoService pedidoSerivce;

	@Transactional
	public void confirmar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.confirmar();

		pedidoRepository.save(pedido);
	}

	@Transactional
	public void cancelar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.cancelar();

		pedidoRepository.save(pedido);
	}

	@Transactional
	public void entregar(String codigoPedido) {

		Pedido pedido = pedidoSerivce.buscar(codigoPedido);
		pedido.entregar();

	}

}
