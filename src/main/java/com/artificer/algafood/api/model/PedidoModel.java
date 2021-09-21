package com.artificer.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Pedidos")
@Getter
@Setter
public class PedidoModel  extends RepresentationModel<PedidoModel>{

	private String codigo;
	private BigDecimal valorTotal;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
	private FormaPagamentoModel formaPagamento;
	private EnderecoModel endereco;
	private List<ItemPedidoModel> itens;
}
