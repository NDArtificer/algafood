package com.artificer.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private BigDecimal precoUnitario;
	@Column
	private BigDecimal precoTotal;
	@Column
	private Integer quantidade;
	@Column
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;
	
	
	
}
