package com.artificer.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.artificer.algafood.api.model.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@JsonView({ RestauranteView.Resumo.class, RestauranteView.Nome.class })
	@Schema(example = "1")
	private Long id;

	@JsonView({ RestauranteView.Resumo.class, RestauranteView.Nome.class })
	@Schema(example = "Segredos de Minas")
	private String nome;

	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "12.00")
	private BigDecimal taxaFrete;

	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;

	private Boolean ativo;

	private Boolean aberto;

	private EnderecoModel endereco;

}
