package com.artificer.algafood.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CANCELADO("Cancelado", CRIADO),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	 StatusPedido(String descricao, StatusPedido...statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores= Arrays.asList(statusAnteriores);
	} 

	 public String getDescricao() {
		 return this.descricao;
	 }
	 
	 public Boolean naoPodeAleterarPara(StatusPedido novoStatusPedido) {
		 return !novoStatusPedido.statusAnteriores.contains(this);
	 }
	 
	 
	 public Boolean podeAleterarPara(StatusPedido novoStatusPedido) {
		 return !naoPodeAleterarPara(novoStatusPedido);
	 }
	 
}
