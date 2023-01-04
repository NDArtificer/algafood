package com.artificer.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@NotBlank
	@Schema(example = "Santana de Parnaíba")
	private String nome;
}
