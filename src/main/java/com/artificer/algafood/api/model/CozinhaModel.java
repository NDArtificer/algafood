package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.artificer.algafood.api.model.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Cozinhas")
@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	@JsonView(RestauranteView.Resumo.class)
	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
