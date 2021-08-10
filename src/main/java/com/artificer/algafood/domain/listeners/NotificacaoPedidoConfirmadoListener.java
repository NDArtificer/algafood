package com.artificer.algafood.domain.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.artificer.algafood.domain.event.PedidoConfirmadoEvent;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.service.EnvioEmailService;
import com.artificer.algafood.domain.service.EnvioEmailService.Message;

@Component
public class NotificacaoPedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioService;

	@TransactionalEventListener
	private void confimarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();

		var message = Message.builder()
				.subject(pedido.getRestaurante()
				.getNome() + " Pedido Confirmado")
				.body("pedido-confirmado.html").model("pedido", pedido)
				.recipient(pedido.getCliente().getEmail())
				.build();
		
		envioService.enviar(message);
	}

}
