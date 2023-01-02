package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.artificer.algafood.api.model.views.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Cozinhas")
@Getter
@Setter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "1")
	private Long id;

	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "Brasileira")
	private String nome;

}
