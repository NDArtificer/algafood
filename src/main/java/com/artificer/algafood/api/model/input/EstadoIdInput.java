package com.artificer.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

	@NotNull
	@Schema(example = "1")
	private Long id;

}
