package com.artificer.algafood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInput {

	@NotBlank
	@Schema(example = "38400-000")
	private String cep;

	@NotBlank
	@Schema(example = "Rua Floriano Peixoto")
	private String logradouro;

	@NotBlank
	@Schema(example = "1500")
	private String numero;
	@Schema(example = "Apto 901")
	private String complemento;

	@NotBlank
	@Schema(example = "Centro")
	private String bairro;

	@Valid
	@NotNull
	private CidadeIdInput cidade;
}
