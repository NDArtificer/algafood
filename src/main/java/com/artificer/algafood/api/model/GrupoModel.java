package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Grupos")
@Setter
@Getter
public class GrupoModel extends RepresentationModel<GrupoModel> {

	private Long id;
	private String nome;
}
