package com.artificer.algafood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = 0;
		}

		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}

}
