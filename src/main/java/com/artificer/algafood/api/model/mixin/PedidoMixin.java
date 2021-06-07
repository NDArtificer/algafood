package com.artificer.algafood.api.model.mixin;

import com.artificer.algafood.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PedidoMixin {
	
	@JsonIgnore
	private Endereco endereco;

}
