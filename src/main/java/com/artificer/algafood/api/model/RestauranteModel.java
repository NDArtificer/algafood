package com.artificer.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.artificer.algafood.api.model.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel extends RepresentationModel<RestauranteModel>{

	@JsonView({ RestauranteView.Resumo.class, RestauranteView.Nome.class })
	private Long id;

	@JsonView({ RestauranteView.Resumo.class, RestauranteView.Nome.class })
	private String nome;

	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;

	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;

	private Boolean ativo;

	private Boolean aberto;

	private EnderecoModel endereco;

}
