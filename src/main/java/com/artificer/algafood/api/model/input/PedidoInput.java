package com.artificer.algafood.api.model.input;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInput {

	@Valid
	@NotNull
	private RestauranteIdInput restaurante;

	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;

	@Valid
	@NotNull
	private EnderecoInput endereco;

	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itens;

}
