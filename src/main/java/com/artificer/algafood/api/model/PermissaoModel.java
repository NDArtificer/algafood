package com.artificer.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Permissoes")
@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

	private Long id;

	private String nome;
	
	private String descricao;
	
}
