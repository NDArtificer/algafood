package com.artificer.algafood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.enums.StatusPedido;
import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.model.Pedido;

@Service
public class AlteracaoPedidoService {

	@Autowired
	private CadastroPedidoService pedidoSerivce;

	@Transactional
	public void confirmar(Long pedidoId) {

		Pedido pedido = pedidoSerivce.buscar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("O status do pedido %d não pode ser alterado de %s para %s!",
					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
		
		
	}

}
