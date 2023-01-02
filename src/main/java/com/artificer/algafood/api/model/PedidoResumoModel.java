package com.artificer.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")

@Relation(collectionRelation = "Pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

	@Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
	private String codigo;

	@Schema(example = "298.90")
	private BigDecimal valorTotal;

	@Schema(example = "308.90")
	private BigDecimal subtotal;

	@Schema(example = "10.00")
	private BigDecimal taxaFrete;

	@Schema(example = "CRIADO")
	private String statusPedido;

	@Schema(example = "2019-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;

	private RestauranteResumoModel restaurante;

	private UsuarioModel cliente;
}
