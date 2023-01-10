package com.artificer.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
