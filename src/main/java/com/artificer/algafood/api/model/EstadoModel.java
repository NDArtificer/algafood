package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Estados")
@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "São Paulo")
	private String nome;

}
