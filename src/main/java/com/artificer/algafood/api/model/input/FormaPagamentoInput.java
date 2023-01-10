package com.artificer.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

	@NotBlank
	@Schema(example = "Cartão de crédito")
	private String descricao;
}
