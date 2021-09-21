package com.artificer.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "ItemPedido")
@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

	private Long produtoId;
	private String produtoNome;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;

}
