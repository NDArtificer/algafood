package com.artificer.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteSummaryModel extends RepresentationModel<RestauranteSummaryModel>{
	
	private Long id;

	private String nome;

	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

}
