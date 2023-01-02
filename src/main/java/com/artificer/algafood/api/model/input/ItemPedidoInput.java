package com.artificer.algafood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {
	
	@NotNull
	@Schema(example = "1")
	private Long produtoId;
	
	@NotNull
	@PositiveOrZero
	@Schema(example = "2")
	private Integer quantidade;
	
	@Schema(example = "Menos picante, por favor")
	private String observacao;
}
