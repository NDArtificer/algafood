package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Estados")
@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {

	private Long id;
	
	private String nome;

}
