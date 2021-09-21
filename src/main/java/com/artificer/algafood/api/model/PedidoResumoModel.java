package com.artificer.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;


//@JsonFilter("pedidoFilter")

@Relation(collectionRelation = "Pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	private String codigo;
	private BigDecimal valorTotal;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
}
