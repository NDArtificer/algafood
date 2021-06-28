package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.PedidoNaoEncontradoException;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId).orElseThrow(()-> new PedidoNaoEncontradoException(pedidoId));
	}
	
}
