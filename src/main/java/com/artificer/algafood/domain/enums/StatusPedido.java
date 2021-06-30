package com.artificer.algafood.domain.enums;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CANCELADO("Cancelado"),
	CONFIRMADO("Confirmado"),
	ENTREGUE("Entregue");

	private String descricao;
	
	 StatusPedido(String descricao) {
		this.descricao = descricao; 	
	} 

	 public String getDescricao() {
		 return this.descricao;
	 }
}
