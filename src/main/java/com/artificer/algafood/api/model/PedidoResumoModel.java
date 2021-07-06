package com.artificer.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;


//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

	private String codigo;
	private BigDecimal valorTotal;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
}
